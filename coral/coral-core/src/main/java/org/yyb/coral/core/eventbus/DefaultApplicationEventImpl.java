package org.yyb.coral.core.eventbus;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.common.utils.ConfigUtils;

/**
 * 默认本地发布的事件，不跨JVM；当事件类型发送本地或远程可配置时使用;由于消息为发布订阅模式，对于接受到消息并发处理还是只能处理一次由监听业务应用判断
 * 
 * @author: yybg
 * @date: 2017年11月3日 下午2:15:52
 */
public class DefaultApplicationEventImpl extends AbstractApplicationEvent {

    private static final long serialVersionUID = -5435928435086851932L;

    /**
     * EVENTBUS module配置
     */
    private static final String CORAL_EVENTBUS_MODULE = "eventbus";

    /**
     * EVENTBUS group为identifyevent配置
     */
    private static final String CORAL_EVENTBUS_GROUP_IDENTIFYEVENT = "identifyevent";

    public DefaultApplicationEventImpl(Object eventData) {
        super(eventData);
        setIdentifyEvent();
    }

    public DefaultApplicationEventImpl(Object eventData, String eventType, String applicationId) {
        super(eventData, eventType, applicationId);
        setIdentifyEvent();
    }

    public DefaultApplicationEventImpl(Object eventData, String eventType) {
        super(eventData, eventType);
        setIdentifyEvent();
    }

    private void setIdentifyEvent() {
        setEventIdentification(getIdentifyEvent(getEventType()).getId());
    }

    /**
     * 获取配置的事件类型标识，为local或remote,默认为:local;默认在coralconfig配置要远程或本地发送
     * 
     * @param eventType
     * @return
     */
    private AppIdentificationEventEnum getIdentifyEvent(String eventType) {
        if (StringUtils.isNotBlank(eventType)) {
            String identifyValue = ConfigUtils
                    .getConfigValue(CORAL_EVENTBUS_MODULE, CORAL_EVENTBUS_GROUP_IDENTIFYEVENT, eventType, AppIdentificationEventEnum.LOCAL.getId());
            return AppIdentificationEventEnum.getInitType(identifyValue);
        }
        return AppIdentificationEventEnum.LOCAL;

    }
}
