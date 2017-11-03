package org.yyb.coral.core.eventbus;

/**
 * 事件总线事件
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午2:16:03
 */
public interface IApplicationEvent {
    /**
     * 获取事件发布时间戳
     * 
     * @return
     */
    public long getTimestamp();

    /**
     * 获取发布事件的所属应用ID
     * 
     * @return
     */
    public String getApplicationId();

    /**
     * 设置发布事件的应用ID
     * 
     * @param applicationId
     */
    public void setApplicationId(String applicationId);

    /**
     * 获取请求ID，UUID，唯一
     * 
     * @return
     */
    public String getRequestId();

    /**
     * 设置请求ID，UUID，唯一
     * 
     * @param requestId
     */
    public void setRequestId(String requestId);

    /**
     * 获取事件标识，remote OR local
     * 
     * @return
     */
    public String getEventIdentification();

    /**
     * 设置事件标识，remote OR local
     * 
     * @param eventIdentification
     */
    public void setEventIdentification(String eventIdentification);

    /**
     * 获取事件类型，具体业务监听一般监听自己感兴趣的事件
     * 
     * @return
     */
    public String getEventType();

    /**
     * 设置事件类型，具体业务监听一般监听自己感兴趣的事件
     * 
     * @param eventType
     */
    public void setEventType(String eventType);

    /**
     * 获取事件数据
     * 
     * @return
     */
    public Object getEventData();

    /**
     * 设置事件数据
     * 
     * @param eventData
     */
    public void setEventData(Object eventData);

    /**
     * 有些应用判断是否处理此事件时，不需要反序列化具体事件数据 获取事件数据，byte[]
     * 
     * @return
     */
    public byte[] getEventDataByte();

    /**
     * 设置事件数据，byte[]
     * 
     * @param eventDataByte
     */
    public void setEventDataByte(byte[] eventDataByte);

    /**
     * 获取要发送的应用ID，每个JVM一个应用ID，发布事件本JVM应用ID
     * 
     * @return
     */
    public String getToApplicationId();

    /**
     * 设置应用ID，一般不需要设置
     * 
     * @param toApplicationId
     */
    public void setToApplicationId(String toApplicationId);
}
