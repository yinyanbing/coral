package org.yyb.coral.core.redis;

import org.redisson.api.RedissonClient;
import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * redisson客户端提供者接口
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午4:44:11
 */
@Spi(scope = Scope.SINGLETON)
public interface IRedissonProvider {
    /**
     * 获取redis默认配置组default的客户端Redisson
     * 
     * @return
     */
    public RedissonClient getRedissonClientDefault();

    /**
     * 获取redis某配置组的客户端Redisson
     * 
     * @param configGroup
     *            reids在配置文件中可配置多组，用于不同的业务场景使用
     * @return
     */
    public RedissonClient getRedissonClient(String configGroup);

}
