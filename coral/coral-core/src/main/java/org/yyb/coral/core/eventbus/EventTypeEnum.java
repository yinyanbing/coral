package org.yyb.coral.core.eventbus;

import org.apache.commons.lang3.StringUtils;

/**
 * CORAL枚举事件
 * 
 * @author: yybg
 * @date: 2017年11月3日 下午2:49:42
 */
public enum EventTypeEnum {
    SYNCINVOKE("syncinvoke", "同步转异步调用事件事件"),;

    private String id;

    private String title;

    EventTypeEnum(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static EventTypeEnum getCacheProviderType(String cacheType) {
        if (StringUtils.isBlank(cacheType)) {
            return null;
        }
        for (EventTypeEnum enumeData : EventTypeEnum.values()) {
            if (enumeData.getId().equalsIgnoreCase(cacheType)) {
                return enumeData;
            }
        }
        return null;
    }
}
