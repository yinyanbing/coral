package org.yyb.coral.core.eventbus;

import java.io.Serializable;

import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.DataSerializeUtils;

import com.google.common.base.MoreObjects;

/**
 * 应用事件
 * 
 * @author: yybg
 * @date: 2017年10月13日 下午5:01:39
 */
public abstract class AbstractApplicationEvent implements IApplicationEvent, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5414862924494893220L;

    private final long timestamp;

    // private static final String EVET_SOURCE = "Spring";

    /**
     * 应用ID，一般一个JVM一个应用id，有些事件是本应用不处理的
     */
    private String applicationId = Constants.SP_APPLICATIONID;

    /**
     * 请求ID
     */
    private String requestId = BaseUtils.getUUIDText();

    /**
     * 事件标识，如local、remote,具体实现设置
     */
    private String eventIdentification = null;

    /**
     * 事件类型，订阅时要事件类型一致
     */
    private String eventType = null;

    /**
     * 事件数据
     */
    private Object eventData = null;

    /**
     * 事件数据二进制数据，为了跨平台防止反序列化异常
     */
    private byte[] eventDataByte = null;

    /**
     * 要接受的应用ID，如果需要指定应用接受事件单独处理的话
     */
    private String toApplicationId = null;

    public AbstractApplicationEvent(Object eventData) {
        this.eventData = eventData;
        this.timestamp = System.currentTimeMillis();
    }

    public AbstractApplicationEvent(Object eventData, String eventType) {
        this.eventData = eventData;
        this.eventType = eventType;
        this.timestamp = System.currentTimeMillis();
    }

    public AbstractApplicationEvent(Object eventData, String eventType, String applicationId) {
        this.eventData = eventData;
        this.eventType = eventType;
        this.applicationId = applicationId;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public final long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String getApplicationId() {
        return applicationId;
    }

    @Override
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String getEventIdentification() {
        return eventIdentification;
    }

    @Override
    public void setEventIdentification(String eventIdentification) {
        this.eventIdentification = eventIdentification;
        if (AppIdentificationEventEnum.REMOTE.getId().equals(eventIdentification)) {
            // 事件数据转化为byte数组，应用根据实际情况决定是否监听处理此种消息事件
            if (eventData != null && eventDataByte == null) {
                eventDataByte = DataSerializeUtils.serializeValue(eventData);
                eventData = null;
            }
        }
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public Object getEventData() {
        if (AppIdentificationEventEnum.REMOTE.getId().equals(eventIdentification)) {
            // 事件数据转化为byte数组，应用根据实际情况决定是否监听处理此种消息事件
            if (eventData == null && eventDataByte != null) {
                eventData = DataSerializeUtils.deserializedValue(eventDataByte);
                eventDataByte = null;
            }
        }
        return eventData;
    }

    @Override
    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }

    @Override
    public byte[] getEventDataByte() {
        return eventDataByte;
    }

    @Override
    public void setEventDataByte(byte[] eventDataByte) {
        this.eventDataByte = eventDataByte;
    }

    @Override
    public String getToApplicationId() {
        return toApplicationId;
    }

    @Override
    public void setToApplicationId(String toApplicationId) {
        this.toApplicationId = toApplicationId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("applicationId", applicationId).add("requestId", requestId)
                .add("eventIdentification", eventIdentification).add("eventType", eventType).add("eventData", eventData)
                .add("toApplicationId", toApplicationId).omitNullValues().toString();
    }

}
