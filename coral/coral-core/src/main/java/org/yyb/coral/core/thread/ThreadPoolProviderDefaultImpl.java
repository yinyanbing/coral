package org.yyb.coral.core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.provider.AbstractIntegrateClientProvider;
import org.yyb.coral.core.provider.IntegrateOrderEnum;
import org.yyb.coral.core.thread.config.ThreadPoolConfigModel;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * 默认的线程池提供者
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午4:50:49
 */
@SpiMeta(name = "default")
public class ThreadPoolProviderDefaultImpl extends AbstractIntegrateClientProvider<ListeningExecutorService> implements IThreadPoolProvider {

    public ThreadPoolProviderDefaultImpl() {
        super(Constants.CORAL_THREADPOOL, "coral");
    }

    @Override
    protected Object doMakeClientPoolInternal(IConfigModel configModel) {
        if (configModel instanceof ThreadPoolConfigModel) {
            ThreadPoolConfigModel threadpoolConfigModel = (ThreadPoolConfigModel) configModel;

            ThreadInitTypeEnum initTypeEnum = ThreadInitTypeEnum.getInitType(threadpoolConfigModel.getThreadPoolType());
            ExecutorService executorService = null;
            ScheduledExecutorService scheduledExecutorService = null;
            switch (initTypeEnum) {
            case NORMAL:
                executorService = createNormalThreadPool(
                        configModel.getId(),
                        threadpoolConfigModel.getCorePoolSize(),
                        threadpoolConfigModel.getMaximumPoolSize(),
                        threadpoolConfigModel.getKeepAliveTime(),
                        threadpoolConfigModel.getWorkQueueSize());
                break;
            case SCHEDULED:
                scheduledExecutorService = createScheduledThreadPool(configModel.getId(), threadpoolConfigModel.getCorePoolSize());
                break;
            default:
                executorService = createNormalThreadPool(
                        configModel.getId(),
                        threadpoolConfigModel.getCorePoolSize(),
                        threadpoolConfigModel.getMaximumPoolSize(),
                        threadpoolConfigModel.getKeepAliveTime(),
                        threadpoolConfigModel.getWorkQueueSize());
                break;
            }
            if (executorService != null) {
                ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
                return listeningExecutorService;
            }
            if (scheduledExecutorService != null) {
                ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(scheduledExecutorService);
                return listeningExecutorService;
            }
        }
        return null;
    }

    @Override
    protected ListeningExecutorService doGetClientInternal(Object clientPool) {
        if (clientPool != null) {
            return (ListeningExecutorService) clientPool;
        }
        return null;
    }

    @Override
    public ListeningExecutorService getExecutorServiceDefault() {
        return getClientDefault();
    }

    @Override
    public ListeningExecutorService getExecutorService(String configGroup) {
        return getClient(configGroup);
    }

    private ExecutorService createNormalThreadPool(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, int workQueueSize) {
        if (maximumPoolSize <= 0) {
            maximumPoolSize = Integer.MAX_VALUE;
        }
        // 线程池执行队列
        BlockingQueue<Runnable> workQueue = null;
        if (workQueueSize <= 0) {
            workQueue = new LinkedBlockingQueue<Runnable>();
        } else {
            workQueue = new LinkedBlockingQueue<Runnable>(workQueueSize);
        }
        CoralThreadFactory coralThreadFactory = new CoralThreadFactory(name);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ExecutorService executorService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue,
                coralThreadFactory, handler);
        return executorService;
    }

    private ScheduledExecutorService createScheduledThreadPool(String name, int corePoolSize) {
        CoralThreadFactory coralThreadFactory = new CoralThreadFactory(name);
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(corePoolSize, coralThreadFactory);
        return executorService;
    }

    @Override
    public int getOrder() {
        return IntegrateOrderEnum.THREADPOOL.getOrder();
    }

    @Override
    protected void doDestroyClientPoolInternal(Object clientPool) {
        if (clientPool != null) {
            ((ListeningExecutorService) clientPool).shutdown();
        }
    }
}
