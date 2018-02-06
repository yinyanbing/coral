package org.yyb.coral.core.eventbus.provider.redis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.DataSerializeUtils;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.eventbus.provider.ISubPubProvider;

import redis.clients.jedis.BinaryJedisPubSub;

/**
 * 事件消息redis订阅
 * 
 * @ClassName: EventMessageSubscriberListener
 * @author: yybg
 * @date: 2016年12月20日 下午2:03:03
 */
public class EventMessageSubscriberListener extends BinaryJedisPubSub {

    private static final Logger logger = LoggerFactory.getLogger(EventMessageSubscriberListener.class);

    /**
     * 默认事件的执行结果过期秒数，10分钟
     */
    private static final int EVENT_EXPIRE_SECONDDS = 60 * 10;

    /**
     * 订阅channel
     */
    private byte[] subChannel = null;

    private IRedisMessageSubHander messageSubHander = null;

    // 配置文件中配置的监听处理的事件类型KEY，如果没有数据，则监听处理所有
    // private static final String MATCH_EVENT_TYPE_KEY =
    // "EventBusListenerEventTypes";

    // private Set<String> mathEventSets = new HashSet<String>();

    // private boolean needMathEventType = false;

    public EventMessageSubscriberListener(byte[] channel, IRedisMessageSubHander messageSubHander) {
        this.subChannel = channel;
        this.messageSubHander = messageSubHander;
        if (subChannel == null) {
            subChannel = ISubPubProvider.PUBSUB_CHANNEL_BYTE;
        }
    }

    @Override
    public void onMessage(byte[] channel, byte[] message) {
        // 接受消息，并处理
        if (Arrays.equals(subChannel, channel)) {
            try {
                Object messageObj = DataSerializeUtils.deserializedValue(message);
                if (messageObj != null && messageObj instanceof IApplicationEvent) {
                    IApplicationEvent applicationEvent = (IApplicationEvent) messageObj;
                    messageSubHander.handerMessage(applicationEvent);
                }
            } catch (Exception e) {
                logger.error(BaseUtils.getLogText("Eventbus subscribe redis topic 【%s】 deal error!", Constants.CORAL_EVENTBUS_CHANNEL), e);
            }
        }
    }

    @Override
    public void onPMessage(byte[] pattern, byte[] channel, byte[] message) {
        // super.onPMessage(pattern, channel, message);
    }

    @Override
    public void onSubscribe(byte[] channel, int subscribedChannels) {
        // super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(byte[] channel, int subscribedChannels) {
        // super.onUnsubscribe(channel, subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(byte[] pattern, int subscribedChannels) {
        // super.onPUnsubscribe(pattern, subscribedChannels);
    }

    @Override
    public void onPSubscribe(byte[] pattern, int subscribedChannels) {
        // super.onPSubscribe(pattern, subscribedChannels);
    }
}
