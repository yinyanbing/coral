package org.yyb.coral.core.eventbus;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yyb.coral.core.CommonTest;
import org.yyb.coral.core.eventbus.listener.AbstractEventListener;
import org.yyb.coral.core.eventbus.publisher.IEventPublishable;

/**
 * 事件总线单测，需配置好redis
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:07:06
 */
public class EventbusTest extends CommonTest {
    /**
     * 实例注册业务监听器
     */
    private EventBusListenerTest busListenerTest = new EventBusListenerTest();

    @Test
    public void testLocalEvent() {
        IEventPublishable eventPublishable = EventBusProviderFactory.getEventBusProvider();
        IApplicationEvent appEvent = new LocalApplicationEventImpl("data01", "01");
        boolean result = eventPublishable.publish(appEvent);
        assertEquals(true, result);
    }

    @Test
    public void testRemoteEvent() {
        IEventPublishable eventPublishable = EventBusProviderFactory.getEventBusProvider();
        IApplicationEvent appEvent = new RemoteApplicationEventImpl("data01", "01");
        boolean result = eventPublishable.publish(appEvent);
        assertEquals(true, result);
    }

    private static class EventBusListenerTest extends AbstractEventListener<IApplicationEvent> {
        public EventBusListenerTest() {
            super("test01");
        }

        @Override
        protected void onAppEventInternal(IApplicationEvent event) {
            System.err.println("System" + event.getEventType() + ":" + event.getEventData() + ":" + event.getEventIdentification());
            assertEquals("01:data01", event.getEventType() + ":" + event.getEventData());
        }

    }
}
