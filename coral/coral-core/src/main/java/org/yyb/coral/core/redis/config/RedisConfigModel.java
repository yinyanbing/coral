package org.yyb.coral.core.redis.config;

import org.yyb.coral.core.configmodel.provider.MiddlewareConfigModel;

/**
 * jedis配置信息
 * 
 * @author: yybg
 * @date: 2017年7月19日 下午2:47:30
 */
public class RedisConfigModel extends MiddlewareConfigModel {

    private static final long serialVersionUID = -3886080764611430651L;

    /**
     * Redisson初始化方式;SINGLETON:单例模式(host和port非空)；CLUSTER：集群模式(clusterNodes非空)
     */
    private String redissonInitType = "SINGLETON";

    public String getRedissonInitType() {
        return redissonInitType;
    }

    public void setRedissonInitType(String redissonInitType) {
        this.redissonInitType = redissonInitType;
    }

}
