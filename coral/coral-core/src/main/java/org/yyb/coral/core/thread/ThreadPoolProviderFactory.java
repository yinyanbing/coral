package org.yyb.coral.core.thread;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.Constants;
import org.yyb.coral.common.extension.ExtensionLoader;

/**
 * 线程池提供者工厂
 * 
 * @author: yybg
 * @date: 2017年10月21日 下午9:19:33
 */
public class ThreadPoolProviderFactory {
    private ThreadPoolProviderFactory() {
    }

    /**
     * 获取默认threadpool提供者实现
     * 
     * @return
     */
    public static IThreadPoolProvider getDefaultThreadPoolProvider() {
        return getThreadPoolProvider(Constants.CORAL_DEFAULT);
    }

    /**
     * 获取指定threadpoolProvider通过提供者名字 ，SPI自定义扩展实现
     * 
     * @param providerName
     * @return
     */
    public static IThreadPoolProvider getThreadPoolProvider(String providerName) {
        if (StringUtils.isBlank(providerName)) {
            return null;
        }
        return ExtensionLoader.getExtensionLoader(IThreadPoolProvider.class).getExtension(providerName);
    }

}
