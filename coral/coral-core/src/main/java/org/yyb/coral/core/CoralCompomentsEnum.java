package org.yyb.coral.core;

import org.apache.commons.lang3.StringUtils;

/**
 * coral核心组件枚举
 * 
 * @author: yybg
 * @date: 2017年10月17日 上午11:11:03
 */
public enum CoralCompomentsEnum {
    /**
     * SPI配置组件
     */
    SPICONFIG("spiconfig", "default", "SPI配置组件"),
    /**
     * 事件总线远程组件
     */
    EVENTBUS_REMOTE("eventbus_remote", "redis", "事件总线远程组件"),
    /**
     * 事件总线本地组件
     */
    EVENTBUS_LOCAL("eventbus_local", "guava", "事件总线本地组件");
    /**
     * 组件名
     */
    private String name;

    /**
     * 组件SPI提供者名字
     */
    private String providerName;

    /**
     * 描述
     */
    private String title;

    CoralCompomentsEnum(String code, String providerName, String title) {
        this.name = code;
        this.providerName = providerName;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getTitle() {
        return title;
    }

    public static CoralCompomentsEnum getInitType(String name) {
        if (StringUtils.isNotBlank(name)) {
            for (CoralCompomentsEnum enumeData : CoralCompomentsEnum.values()) {
                if (enumeData.getName().equalsIgnoreCase(name)) {
                    return enumeData;
                }
            }
        }
        return null;
    }
}
