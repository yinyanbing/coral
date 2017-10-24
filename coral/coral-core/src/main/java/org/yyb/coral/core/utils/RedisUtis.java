package org.yyb.coral.core.utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.exception.CoralRuntimeException;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.redis.IJedisProvider;
import org.yyb.coral.core.redis.IRedissonProvider;
import org.yyb.coral.core.redis.RedisProviderFactory;
import org.yyb.coral.core.redis.hander.IRedisLockHander;
import org.yyb.coral.core.redis.hander.RedisInvokeHander;
import org.yyb.coral.core.redis.hander.RedisInvokeResult;
import org.yyb.coral.core.redis.hander.RedisInvokeResultEnum;
import org.yyb.coral.core.redis.hander.RedisInvokeVoidHander;

import redis.clients.jedis.Jedis;

/**
 * Redis基础操作工具类
 * 
 * @author: yybg
 * @date: 2017年10月12日 下午4:51:14
 *
 */
public class RedisUtis {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtis.class);
	/**
	 * 默认的等待获取redis分布式锁时间，单位秒
	 */
	public static final long REDIS_LOCK_WAIT_TIME_DEFAULT = 5;
	/**
	 * 默认的获取redis分布式锁后锁定时间，5秒过期自动解锁，单位秒
	 */
	public static final long REDIS_LOCK_LEASE_TIME_DEFAULT = 5;

	/**
	 * 操作redis 有返回值
	 * 
	 * @param redisInvokeHander
	 * @return
	 */
	public static <T> RedisInvokeResult<T> runWithJedis(RedisInvokeHander<T> redisInvokeHander) {
		return runWithJedis(redisInvokeHander, Constants.CORAL_DEFAULT, Constants.CORAL_DEFAULT);
	}

	/**
	 * 操作redis 无返回值
	 * 
	 * @param redisInvokeVoidHander
	 * @return
	 */
	public static RedisInvokeResult<Void> runWithJedis(final RedisInvokeVoidHander<Void> redisInvokeVoidHander) {
		return runWithJedis(redisInvokeVoidHander, Constants.CORAL_DEFAULT, Constants.CORAL_DEFAULT);
	}

	/**
	 * 操作redis 有返回值
	 * 
	 * @param redisInvokeHander
	 * @param providerName
	 *            jedis提供者名字，一般为default
	 * @param configGroup
	 *            redis配置分组，一般为default
	 * @return
	 */
	public static <T> RedisInvokeResult<T> runWithJedis(RedisInvokeHander<T> redisInvokeHander, String providerName,
			String configGroup) {
		IJedisProvider jedisProvider = RedisProviderFactory.getJedisProvider(providerName);

		Jedis jedis = null;
		RedisInvokeResult<T> result = new RedisInvokeResult<T>();
		try {
			if (jedisProvider != null) {
				jedis = jedisProvider.getJedisClient(configGroup);
				if (jedis != null) {
					T resultValue = redisInvokeHander.invokeWithJedis(jedis);
					result.setValue(resultValue);
					return result;
				} else {
					result.setResultCode(RedisInvokeResultEnum.NOREDISCLIENT);
					logger.warn(
							BaseUtils.getLogText("get jedis is null,please init jedis!providerName=%s,configGroup=%s",
									providerName, configGroup));
				}
			} else {
				result.setResultCode(RedisInvokeResultEnum.NOREDISPROVIDER);
				logger.warn(BaseUtils.getLogText(
						"get JedisProvider is null,please init jedis!providerName=%s,configGroup=%s", providerName,
						configGroup));
			}
		} catch (Exception e) {
			result.setResultCode(RedisInvokeResultEnum.ERROR);
			logger.error(BaseUtils.getLogText("operate jedisClient error!!providerName=%s,configGroup=%s", providerName,
					configGroup), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}

	/**
	 * 操作redis 无返回值
	 * 
	 * @param redisInvokeVoidHander
	 * @param providerName
	 *            jedis提供者名字，一般为default
	 * @param configGroup
	 *            redis配置分组，一般为default
	 * @return
	 */
	public static RedisInvokeResult<Void> runWithJedis(final RedisInvokeVoidHander<Void> redisInvokeVoidHander,
			String providerName, String configGroup) {
		RedisInvokeResult<Void> invokeResult = runWithJedis(new RedisInvokeHander<Void>() {

			@Override
			public Void invokeWithJedis(Jedis jedis) {
				redisInvokeVoidHander.invokeWithJedis(jedis);
				return null;
			}

		}, providerName, configGroup);
		return invokeResult;
	}

	/**
	 * 尝试获取redis分布式锁，并执行业务方法
	 * 
	 * @param lockKey
	 *            锁KEY，非空
	 * @param leaseTime
	 *            获取锁后的锁定时间，一般业务执行时间应小于此时间
	 * @param releaseLock
	 *            执行完业务方法后是否立即释放此锁
	 * @param redisLockHander
	 * @return
	 */
	public static boolean tryLock(String lockKey, long leaseTime, boolean releaseLock,
			IRedisLockHander redisLockHander) {
		return tryLock(Constants.CORAL_DEFAULT, Constants.CORAL_DEFAULT, lockKey, leaseTime, releaseLock,
				redisLockHander);
	}

	/**
	 * 尝试获取redis分布式锁，并执行业务方法
	 * 
	 * @param providerName
	 *            redisson的provider
	 * @param configGroup
	 *            redisson的配置组，默认default
	 * @param lockKey
	 *            锁KEY，非空
	 * @param leaseTime
	 *            获取锁后的锁定时间，一般业务执行时间应小于此时间
	 * @param releaseLock
	 *            执行完业务方法后是否立即释放此锁
	 * @param redisLockHander
	 *            获取锁后执行的业务实现
	 * @return
	 */
	public static boolean tryLock(String providerName, String configGroup, String lockKey, long leaseTime,
			boolean releaseLock, IRedisLockHander redisLockHander) {
		if (StringUtils.isBlank(providerName) || StringUtils.isBlank(configGroup) || StringUtils.isBlank(lockKey)) {
			throw new CoralRuntimeException("providerName or configGroup or lockKey can not be null!");
		}
		if (redisLockHander == null) {
			throw new CoralRuntimeException("redisLockHander can not be null!");
		}
		if (leaseTime <= 0) {
			leaseTime = REDIS_LOCK_LEASE_TIME_DEFAULT;
		}
		RLock rLock = null;
		boolean lockResult = false;
		IRedissonProvider redissonProvider = RedisProviderFactory.getRedissonProvider(providerName);
		if (redissonProvider != null) {
			RedissonClient redissonClient = redissonProvider.getRedissonClient(configGroup);
			if (redissonClient != null) {
				try {
					rLock = redissonClient.getLock(lockKey);
					lockResult = rLock.tryLock(REDIS_LOCK_WAIT_TIME_DEFAULT, leaseTime, TimeUnit.SECONDS);
					if (lockResult) {
						// 调用业务方法
						redisLockHander.execute();
					}
					return lockResult;
				} catch (Exception e) {
					logger.error(
							BaseUtils.getLogText("Reids tryLock is error!providerName=%s,configGroup=%s,lockKey=%s",
									providerName, configGroup, lockKey),
							e);
				} finally {
					if (lockResult && releaseLock) {
						// 执行完业务后，如果获取锁且执行完后需要解锁,则释放锁
						rLock.unlock();
					}
				}
			}
		}
		return false;
	}

	private RedisUtis() {
	};
}
