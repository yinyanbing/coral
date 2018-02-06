package org.yyb.coral.core.redis.hander;

import redis.clients.jedis.Jedis;

/**
 * 有返回值的redis调用接口
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午3:46:13
 */
public interface RedisInvokeHander<T> {

    /**
     * 获取redis客户端不为空时操作
     * 
     * @param jedis
     * @return
     */
    public T invokeWithJedis(Jedis jedis);

}
