package org.yyb.coral.core.eventbus.listener;

import org.yyb.coral.core.eventbus.IApplicationEvent;

import com.google.common.eventbus.Subscribe;

/**
 * 订阅事件监听
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:56:56
 */
public interface IEventListener<E extends IApplicationEvent> {
    /**
     * 事件订阅触发
     * 
     * @param event
     */
    @Subscribe
    public void onAppEvent(E event);
}
