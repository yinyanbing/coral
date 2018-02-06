package org.yyb.coral.core.eventbus.provider.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.DataSerializeUtils;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.provider.AbstractRemoteSubPubProvider;
import org.yyb.coral.core.eventbus.provider.ISubPubProvider;
import org.yyb.coral.core.redis.hander.RedisInvokeResult;
import org.yyb.coral.core.redis.hander.RedisInvokeVoidHander;
import org.yyb.coral.core.utils.RedisUtis;

import redis.clients.jedis.Jedis;

/**
 * redis订阅发布提供者默认实现，开启订阅线程及发布远程事件
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:01:40
 */
@SpiMeta(name = "redis")
public class RedisRemoteSubPubProviderImpl extends AbstractRemoteSubPubProvider {
    private static final Logger logger = LoggerFactory.getLogger(RedisRemoteSubPubProviderImpl.class);

    public RedisRemoteSubPubProviderImpl() {
        super(Constants.CORAL_REDIS);
    }

    @Override
    protected Runnable getSubscriberWorker() {
        RedisSubscriberWorker redisSubscriberWorker = new RedisSubscriberWorker();
        return redisSubscriberWorker;
    }

    @Override
    protected boolean publishEventInternal(IApplicationEvent appEvent) {
        RedisInvokeResult<Void> invokeResult = RedisUtis.runWithJedis(new RedisInvokeVoidHander<Void>() {

            @Override
            public void invokeWithJedis(Jedis jedis) {
                byte[] publishValue = DataSerializeUtils.serializeValue(appEvent);
                jedis.publish(ISubPubProvider.PUBSUB_CHANNEL_BYTE, publishValue);
            }
        });
        if (invokeResult.isSuccess()) {
            return true;
        }
        logger.warn(BaseUtils.getLogText("Eventbus to redis is not success!result=%s", invokeResult.getResultCode()));
        return false;
    }

    @Override
    protected void startInternal() {
    }

    @Override
    protected void stopInternal() {
    }

}
