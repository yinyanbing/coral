package org.yyb.coral.core.thread;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * 获取线程池，返回guava的ListeningExecutorService，用于增强异步执行有返回值任务
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午4:05:15
 */
@Spi(scope = Scope.SINGLETON)
public interface IThreadPoolProvider {
    /**
     * 获取coral默认的线程池，一般使用此线程池，除非特别需要单独的线程池
     * 
     * @return
     */
    public ListeningExecutorService getExecutorServiceDefault();

    /**
     * 获取coral某个特定的配置组线程池，
     * 
     * @param configGroup
     *            线程池在配置文件中可配置多组，用于不同的业务场景使用
     * @return
     */
    public ListeningExecutorService getExecutorService(String configGroup);

}
