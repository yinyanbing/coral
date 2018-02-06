package org.yyb.coral.core.thread.config;

import org.yyb.coral.core.configmodel.AbstractConfigModel;

/**
 * 线程池配置项
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午4:17:49
 */
public class ThreadPoolConfigModel extends AbstractConfigModel {

    /**
     * 
     */
    private static final long serialVersionUID = 9208211272245823009L;

    /**
     * 核心线程数
     */
    private int corePoolSize = 8;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = -1;

    /**
     * 线程保持存活时间
     */
    private long keepAliveTime = 100;

    /**
     * 线程池队列长度
     */
    private int workQueueSize = 100;

    /**
     * 线程池类型，normal或scheduled
     */
    private String threadPoolType = "normal";
    // /**
    // * 时间单元，默认毫秒
    // */
    // private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    // /**
    // * 阻塞队列
    // */
    // private BlockingQueue<Runnable> workQueue = null;
    //
    // private RejectedExecutionHandler handler = new
    // ThreadPoolExecutor.CallerRunsPolicy();

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueueSize() {
        return workQueueSize;
    }

    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    public String getThreadPoolType() {
        return threadPoolType;
    }

    public void setThreadPoolType(String threadPoolType) {
        this.threadPoolType = threadPoolType;
    }

    @Override
    public String toString() {
        return "ThreadPoolConfigModel [corePoolSize=" + corePoolSize + ", maximumPoolSize=" + maximumPoolSize + ", keepAliveTime=" + keepAliveTime
                + ", workQueueSize=" + workQueueSize + ", threadPoolType=" + threadPoolType + "]";
    }

}
