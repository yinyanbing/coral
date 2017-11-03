package org.yyb.coral.core.eventbus.provider.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.provider.AbstractSubscriberWorker;
import org.yyb.coral.core.eventbus.provider.ISubPubProvider;
import org.yyb.coral.core.redis.IJedisProvider;
import org.yyb.coral.core.redis.RedisProviderFactory;

import redis.clients.jedis.Jedis;

/**
 * redis 订阅实现
 * 
 * @author: yybg
 * @date: 2017年10月16日 下午4:50:31
 */
public class RedisSubscriberWorker extends AbstractSubscriberWorker {
    private static final Logger logger = LoggerFactory.getLogger(RedisSubscriberWorker.class);

    /**
     * redis订阅监听
     */
    private EventMessageSubscriberListener eventMessageSubscriberListener = null;

    public RedisSubscriberWorker() {
        super(Constants.CORAL_REDIS, true);
        eventMessageSubscriberListener = new EventMessageSubscriberListener(ISubPubProvider.PUBSUB_CHANNEL_BYTE, new IRedisMessageSubHander() {

            @Override
            public void handerMessage(IApplicationEvent appEvent) {
                onMessage(appEvent);
            }
        });
    }

    @Override
    protected void runInternal() {
        IJedisProvider jedisProvider = RedisProviderFactory.getDefaultJedisProvider();
        if (jedisProvider != null) {
            Jedis proxyJedis = jedisProvider.getJedisClientDefault();
            if (proxyJedis != null) {
                // 当前线程会等待
                proxyJedis.subscribe(eventMessageSubscriberListener, ISubPubProvider.PUBSUB_CHANNEL_BYTE);
            } else {
                logger.warn(BaseUtils.getLogText("Eventbus subscribe get jedis is null,please init jedis!"));
            }
        }
    }

}
