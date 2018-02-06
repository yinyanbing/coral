package org.yyb.coral.core.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * redisson初始化方式
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午4:43:27
 */
public enum RedissonInitTypeEnum {
    /**
     * singleton单例模式
     */
    SINGLETON("SINGLETON", "singleton单例模式"),
    /**
     * 集群模式
     */
    CLUSTER("CLUSTER", "集群模式"),
    /**
     * sentinel哨兵模式
     */
    SENTINEL("SENTINEL", "sentinel哨兵模式"),
    /**
     * MasterSlave模式
     */
    MASTERSLAVE("MASTERSLAVE", "MasterSlave模式");

    private String code;

    private String title;

    RedissonInitTypeEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static RedissonInitTypeEnum getInitType(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (RedissonInitTypeEnum enumeData : RedissonInitTypeEnum.values()) {
                if (enumeData.getCode().equalsIgnoreCase(code)) {
                    return enumeData;
                }
            }
        }
        return RedissonInitTypeEnum.SINGLETON;
    }
}
