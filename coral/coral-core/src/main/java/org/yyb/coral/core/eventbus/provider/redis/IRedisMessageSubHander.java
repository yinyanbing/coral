package org.yyb.coral.core.eventbus.provider.redis;

import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * redis的订阅处理hander
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:00:50
 */
public interface IRedisMessageSubHander {
    /**
     * hander redis message
     * 
     * @param appEvent
     */
    public void handerMessage(IApplicationEvent appEvent);
}
