package org.yyb.coral.core.redis;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.yyb.coral.Constants;
import org.yyb.coral.core.CommonTest;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralConfig;
import org.yyb.coral.core.redis.hander.IRedisLockHander;
import org.yyb.coral.core.redis.hander.RedisInvokeHander;
import org.yyb.coral.core.redis.hander.RedisInvokeResult;
import org.yyb.coral.core.redis.hander.RedisInvokeVoidHander;
import org.yyb.coral.core.utils.RedisUtis;

import junit.framework.Assert;
import redis.clients.jedis.Jedis;

/**
 * REDIS提供者单测
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:07:51
 *
 */
public class RedisProviderTest extends CommonTest {
	@Test
	public void testRedisProviderDefault() {
		IJedisProvider iJedisProvider = RedisProviderFactory.getDefaultJedisProvider();
		Jedis jedis = iJedisProvider.getJedisClientDefault();
		Assert.assertNotNull(jedis);
		RedisUtis.runWithJedis(new RedisInvokeVoidHander() {

			@Override
			public void invokeWithJedis(Jedis jedis) {
				jedis.set("testKey", "testValue");
			}
		});
		RedisInvokeResult<String> result = RedisUtis.runWithJedis(new RedisInvokeHander<String>() {

			@Override
			public String invokeWithJedis(Jedis jedis) {
				return jedis.get("testKey");
			}
		});
		Assert.assertEquals("testValue", result.getValue());
	}

	@Test
	public void testRedisProviderGroup01() {

		IJedisProvider iJedisProvider = RedisProviderFactory.getDefaultJedisProvider();
		Jedis jedis = iJedisProvider.getJedisClient("group01");
		Assert.assertNotNull(jedis);
		RedisUtis.runWithJedis(new RedisInvokeVoidHander() {

			@Override
			public void invokeWithJedis(Jedis jedis) {
				jedis.set("testKey01", "testValue01");
			}
		}, Constants.CORAL_DEFAULT, "group01");
		RedisInvokeResult<String> result = RedisUtis.runWithJedis(new RedisInvokeHander<String>() {

			@Override
			public String invokeWithJedis(Jedis jedis) {
				return jedis.get("testKey01");
			}
		}, Constants.CORAL_DEFAULT, "group01");
		Assert.assertEquals("testValue01", result.getValue());
	}

	@Test
	public void testRedissonProviderDefault() {

		IJedisProvider iJedisProvider = RedisProviderFactory.getDefaultJedisProvider();
		Jedis jedis = iJedisProvider.getJedisClientDefault();
		Assert.assertNotNull(jedis);
		IRedissonProvider redissonProvider = RedisProviderFactory.getDefaultRedissonProvider();
		RedissonClient redisson = redissonProvider.getRedissonClientDefault();
		RMap<String, Integer> map = redisson.getMap("myMapRedisson");
		map.remove("a");
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);

		boolean contains = map.containsKey("a");
		Assert.assertEquals(true, contains);
		Assert.assertEquals(2, map.get("b").intValue());
	}

	@Test
	public void testRedissonLock() {

		boolean result = RedisUtis.tryLock("aaaaaaaaa", 50, true, new IRedisLockHander() {

			@Override
			public void execute() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Assert.assertEquals(true, result);
	}
}
