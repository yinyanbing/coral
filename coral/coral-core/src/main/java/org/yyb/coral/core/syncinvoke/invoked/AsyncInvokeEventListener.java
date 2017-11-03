package org.yyb.coral.core.syncinvoke.invoked;

import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.listener.AbstractEventListener;

/**
 * 事件总线同步转异步监听，事件总线针对
 * 
 * @author yybg
 * @date 2017年11月3日 下午3:56:14
 */
public class AsyncInvokeEventListener extends AbstractEventListener<IApplicationEvent> {
    public static final String EVENTBUS_EVENT_TYPE_SYNCINVOKE = "coral-eventbus-syncinvoke";

    public AsyncInvokeEventListener(String name) {
        super(name);
    }

    /**
     * 监听同步转异步事件，同时处理注册到业务逻辑
     */
    @Override
    protected void onAppEventInternal(IApplicationEvent event) {
        if (EVENTBUS_EVENT_TYPE_SYNCINVOKE.equals(event.getEventType())) {

        }
    }

}
