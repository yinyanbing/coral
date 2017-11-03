package org.yyb.coral.core.eventbus.provider;

import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.DataSerializeUtils;
import org.yyb.coral.core.IStartStopEvent;
import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * 订阅发布提供者，发布时间及继承启动停止
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:00:01
 */
public interface ISubPubProvider extends IStartStopEvent {
    /**
     * 事件总线channel byte
     */
    public static final byte[] PUBSUB_CHANNEL_BYTE = DataSerializeUtils.serializeKey(Constants.CORAL_EVENTBUS_CHANNEL);

    /**
     * 事件总线channel 字符串
     */
    public static final String PUBSUB_CHANNEL = Constants.CORAL_EVENTBUS_CHANNEL;

    /**
     * 发布事件
     * 
     * @param appEvent
     * @return
     */
    public boolean publishEvent(IApplicationEvent appEvent);

}
