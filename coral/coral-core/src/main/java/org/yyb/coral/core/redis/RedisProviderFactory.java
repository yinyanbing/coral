package org.yyb.coral.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.ExtensionLoader;

/**
 * redis客户端集成获取工厂
 * 
 * @author: yybg
 * @date: 2017年10月12日 下午5:08:20
 */
public class RedisProviderFactory {
    private RedisProviderFactory() {
    }

    /**
     * 获取默认jedis提供者实现
     * 
     * @return
     */
    public static IJedisProvider getDefaultJedisProvider() {
        return getJedisProvider(Constants.CORAL_DEFAULT);
    }

    /**
     * 获取指定Jedisprovider通过提供者名字 ，SPI自定义扩展实现
     * 
     * @param providerName
     * @return
     */
    public static IJedisProvider getJedisProvider(String providerName) {
        if (StringUtils.isBlank(providerName)) {
            return null;
        }
        return ExtensionLoader.getExtensionLoader(IJedisProvider.class).getExtension(providerName);
    }

    /**
     * 获取默认Redisson提供者实现
     * 
     * @return
     */
    public static IRedissonProvider getDefaultRedissonProvider() {
        return getRedissonProvider(Constants.CORAL_DEFAULT);
    }

    /**
     * 获取指定RedissonProvider通过提供者名字 ，SPI自定义扩展实现
     * 
     * @param providerName
     * @return
     */
    public static IRedissonProvider getRedissonProvider(String providerName) {
        if (StringUtils.isBlank(providerName)) {
            return null;
        }
        return ExtensionLoader.getExtensionLoader(IRedissonProvider.class).getExtension(providerName);
    }
}
