package org.yyb.coral.core.eventbus;

/**
 * 本地发布的事件，不跨JVM
 * 
 * @ClassName: SpLocalApplicationEvent
 * @author: yybg
 * @date: 2016年12月20日 上午9:55:42
 */
public class LocalApplicationEventImpl extends AbstractApplicationEvent {

    private static final long serialVersionUID = -6897338264420019504L;

    public LocalApplicationEventImpl(Object eventData) {
        super(eventData);
        setEventIdentification(AppIdentificationEventEnum.LOCAL.getId());
    }

    public LocalApplicationEventImpl(Object eventData, String eventType, String applicationId) {
        super(eventData, eventType, applicationId);
        setEventIdentification(AppIdentificationEventEnum.LOCAL.getId());
    }

    public LocalApplicationEventImpl(Object eventData, String eventType) {
        super(eventData, eventType);
        setEventIdentification(AppIdentificationEventEnum.LOCAL.getId());
    }

}
