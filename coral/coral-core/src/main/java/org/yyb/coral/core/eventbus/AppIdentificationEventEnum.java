package org.yyb.coral.core.eventbus;

import org.apache.commons.lang3.StringUtils;

/**
 * 应用事件标识类型枚举
 * 
 * @ClassName: SpAppIdentificationEventEnum
 * @author: yybg
 * @date: 2016年12月23日 下午3:15:58
 */
public enum AppIdentificationEventEnum {
    /**
     * 本地发布(事件)
     */
    LOCAL("local"),
    /**
     * 远程发布(事件)
     */
    REMOTE("remote");
    /**
     * 事件发布标识，local或remote
     */
    private String id;

    AppIdentificationEventEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static AppIdentificationEventEnum getInitType(String id) {
        if (StringUtils.isNotBlank(id)) {
            for (AppIdentificationEventEnum enumeData : AppIdentificationEventEnum.values()) {
                if (enumeData.getId().equalsIgnoreCase(id)) {
                    return enumeData;
                }
            }
        }
        return LOCAL;
    }
}
