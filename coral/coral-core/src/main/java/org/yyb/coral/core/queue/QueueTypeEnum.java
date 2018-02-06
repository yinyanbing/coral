package org.yyb.coral.core.queue;

import org.apache.commons.lang3.StringUtils;

/**
 * 分布式队列类型，redis等
 * 
 * @author yybg
 */
public enum QueueTypeEnum {
    REDIS("redis", "redis队列");

    private String code;

    private String title;

    QueueTypeEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static QueueTypeEnum getQueueTypeEnum(String dataCode) {
        if (StringUtils.isBlank(dataCode)) {
            return QueueTypeEnum.REDIS;
        }
        for (QueueTypeEnum formatEnum : QueueTypeEnum.values()) {
            if (formatEnum.getCode().equalsIgnoreCase(dataCode)) {
                return formatEnum;
            }
        }
        return QueueTypeEnum.REDIS;
    }
}
