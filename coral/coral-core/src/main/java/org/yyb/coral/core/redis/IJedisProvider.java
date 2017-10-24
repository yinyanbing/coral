package org.yyb.coral.core.redis;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

import redis.clients.jedis.Jedis;

/**
 * Jedis客户端提供者接口
 * 
 * @author: yybg
 * @date: 2017年10月10日 上午10:09:16
 */
@Spi(scope = Scope.SINGLETON)
public interface IJedisProvider {
	/**
	 * 获取redis默认default配置组的客户端jedis
	 * 
	 * @return
	 */
	public Jedis getJedisClientDefault();

	/**
	 * 获取redis某配置组的客户端jedis
	 * 
	 * @param configGroup
	 *            reids在配置文件中可配置多组，用于不同的业务场景使用
	 * @return
	 */
	public Jedis getJedisClient(String configGroup);

}
