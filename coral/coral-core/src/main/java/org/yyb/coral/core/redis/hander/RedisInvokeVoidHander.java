package org.yyb.coral.core.redis.hander;

import redis.clients.jedis.Jedis;

/**
 * 没返回值的redis调用接口
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午3:46:28
 *
 */
public interface RedisInvokeVoidHander<T> {

	/**
	 * 获取redis客户端不为空时操作
	 * 
	 * @param jedis
	 */
	public void invokeWithJedis(Jedis jedis);

}
