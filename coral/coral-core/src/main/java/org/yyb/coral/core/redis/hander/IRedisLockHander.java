package org.yyb.coral.core.redis.hander;

/**
 * 获取redis锁后执行业务hander
 * 
 * @author: yybg
 * @date: 2017年10月13日 下午3:20:18
 *
 */
public interface IRedisLockHander {
	/**
	 * 获取redis锁 业务执行
	 */
	public void execute();
}
