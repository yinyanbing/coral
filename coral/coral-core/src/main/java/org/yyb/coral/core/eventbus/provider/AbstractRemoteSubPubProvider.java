package org.yyb.coral.core.eventbus.provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.yyb.coral.core.ICoralName;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.thread.CoralThreadFactory;

/**
 * 远程订阅发布实现抽象类
 * 
 * @author: yybg
 * @date: 2017年10月16日 下午4:18:20
 */
public abstract class AbstractRemoteSubPubProvider implements IRemoteSubPubProvider, ICoralName {
    /**
     * 订阅发布的实现名称，如redis、RocketMQ等
     */
    private String name = null;

    public AbstractRemoteSubPubProvider(String name) {
        super();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void start() {
        CoralThreadFactory coralThreadFactory = new CoralThreadFactory(name + "-remote subscribe");
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1),
                coralThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.submit(getSubscriberWorker());
        startInternal();
    }

    @Override
    public void stop() {
        stopInternal();
    }

    @Override
    public boolean publishEvent(IApplicationEvent appEvent) {
        return publishEventInternal(appEvent);
    }

    /**
     * 订阅具体实现，可继承AbstarctSubscriberWorker
     * 
     * @return
     */
    protected abstract Runnable getSubscriberWorker();

    /**
     * 子类发布事件实现
     * 
     * @param appEvent
     * @return
     */
    protected abstract boolean publishEventInternal(IApplicationEvent appEvent);

    /**
     * 子类实现启动处理
     */
    protected abstract void startInternal();

    /**
     * 子类实现停止销毁处理
     */
    protected abstract void stopInternal();
}
