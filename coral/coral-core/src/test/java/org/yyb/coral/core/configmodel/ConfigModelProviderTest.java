package org.yyb.coral.core.configmodel;

import org.junit.Test;
import org.yyb.coral.Constants;
import org.yyb.coral.core.redis.config.RedisConfigModel;

import junit.framework.Assert;
import redis.clients.jedis.Jedis;

/**
 * 配置model提供者 测试
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:06:45
 *
 */
public class ConfigModelProviderTest {
	@Test
	public void testRedisConfigModelDefault() {
		RedisConfigModel configModel = (RedisConfigModel) ConfigModelProviderFactory
				.getDefaultConfigModel(Constants.CORAL_REDIS);
		Assert.assertNotNull(configModel);
		Assert.assertEquals("127.0.0.1", configModel.getHost());
		Assert.assertEquals(6379, configModel.getPort());
	}

	@Test
	public void testRedisConfigModelGroup01() {
		RedisConfigModel configModel = (RedisConfigModel) ConfigModelProviderFactory
				.getConfigModel(Constants.CORAL_REDIS, "group01");
		Assert.assertNotNull(configModel);
		Assert.assertEquals("127.0.0.1", configModel.getHost());
		Assert.assertEquals(6379, configModel.getPort());
	}
}
