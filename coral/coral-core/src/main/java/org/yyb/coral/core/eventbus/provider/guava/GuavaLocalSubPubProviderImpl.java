package org.yyb.coral.core.eventbus.provider.guava;

import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.provider.AbstractLocalSubPubProvider;
import org.yyb.coral.core.thread.IThreadPoolProvider;
import org.yyb.coral.core.thread.ThreadPoolProviderFactory;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * 本地事件发布GUAVA实现
 * 
 * @author: yybg
 * @date: 2017年10月17日 下午5:30:04
 */

@SpiMeta(name = "guava")
public class GuavaLocalSubPubProviderImpl extends AbstractLocalSubPubProvider {
    private static final String GUAVA_EVENTBUSS_NAME = "coral-eventbus";

    /**
     * 异步执行的事件总线
     */
    private AsyncEventBus guavaEventbus = null;

    public GuavaLocalSubPubProviderImpl() {
        super("guava");
    }

    @Override
    public String getName() {
        return "guava";
    }

    @Override
    public void registerListener(Object obj) {
        guavaEventbus.register(obj);
    }

    @Override
    protected boolean publishEventInternal(IApplicationEvent appEvent) {
        guavaEventbus.post(appEvent);
        return true;
    }

    @Override
    protected void startInternal() {
        // 本地事件发布异步处理
        IThreadPoolProvider threadPoolProvider = ThreadPoolProviderFactory.getDefaultThreadPoolProvider();
        ListeningExecutorService executorService = threadPoolProvider.getExecutorServiceDefault();
        guavaEventbus = new AsyncEventBus(GUAVA_EVENTBUSS_NAME, executorService);
    }

    @Override
    protected void stopInternal() {
    }

}
