package org.yyb.coral.core.eventbus;

/**
 * 远程发布的事件，跨JVM
 * 
 * @ClassName: SpLocalApplicationEvent
 * @author: yybg
 * @date: 2016年12月20日 上午9:55:42
 */
public class RemoteApplicationEventImpl extends AbstractApplicationEvent {

    private static final long serialVersionUID = 582775961374512116L;

    public RemoteApplicationEventImpl(Object eventData) {
        super(eventData);
        setEventIdentification(AppIdentificationEventEnum.REMOTE.getId());
    }

    public RemoteApplicationEventImpl(Object eventData, String eventType, String applicationId) {
        super(eventData, eventType, applicationId);
        setEventIdentification(AppIdentificationEventEnum.REMOTE.getId());
    }

    public RemoteApplicationEventImpl(Object eventData, String eventType) {
        super(eventData, eventType);
        setEventIdentification(AppIdentificationEventEnum.REMOTE.getId());
    }

}
