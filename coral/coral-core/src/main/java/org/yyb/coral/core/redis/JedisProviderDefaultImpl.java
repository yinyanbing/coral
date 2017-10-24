package org.yyb.coral.core.redis;

import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.provider.AbstractIntegrateClientProvider;
import org.yyb.coral.core.provider.IntegrateOrderEnum;
import org.yyb.coral.core.redis.config.RedisConfigModel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis客户端初始化
 * 
 * @author: yybg
 * @date: 2017年10月12日 上午10:49:44
 *
 */
@SpiMeta(name = "default")
public class JedisProviderDefaultImpl extends AbstractIntegrateClientProvider<Jedis> implements IJedisProvider {

	public JedisProviderDefaultImpl() {
		super(Constants.CORAL_REDIS, "Jedis");
	}

	@Override
	protected Object doMakeClientPoolInternal(IConfigModel configModel) {
		if (configModel instanceof RedisConfigModel) {
			RedisConfigModel redisConfigModel = (RedisConfigModel) configModel;
			JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxTotal(redisConfigModel.getMaxTotal());
			jedisPoolConfig.setMaxIdle(redisConfigModel.getMaxIdle());
			jedisPoolConfig.setMinIdle(redisConfigModel.getMinIdle());
			jedisPoolConfig.setMaxWaitMillis(redisConfigModel.getMaxWaitMillis());
			jedisPoolConfig.setTestOnBorrow(redisConfigModel.isTestOnBorrow());
			JedisPool jedisPoolTemp = new JedisPool(jedisPoolConfig, redisConfigModel.getHost(),
					redisConfigModel.getPort(), redisConfigModel.getTimeOut(), redisConfigModel.getPassWord());
			return jedisPoolTemp;
		}
		return null;
	}

	@Override
	protected Jedis doGetClientInternal(Object clientPool) {
		if (clientPool != null) {
			return ((JedisPool) clientPool).getResource();
		}
		return null;
	}

	@Override
	public Jedis getJedisClientDefault() {
		return getClientDefault();
	}

	@Override
	public Jedis getJedisClient(String configGroup) {
		return getClient(configGroup);
	}

	@Override
	public int getOrder() {
		return IntegrateOrderEnum.REDIS.getOrder();
	}

	@Override
	protected void doDestroyClientPoolInternal(Object clientPool) {
		if (clientPool != null) {
			((JedisPool) clientPool).close();
		}
	}

}
