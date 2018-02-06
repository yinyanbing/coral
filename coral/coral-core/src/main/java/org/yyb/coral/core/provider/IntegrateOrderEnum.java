package org.yyb.coral.core.provider;

/**
 * 三方集成启动及销毁顺序
 * 
 * @author: yybg
 * @date: 2017年10月23日 上午11:24:59
 */
public enum IntegrateOrderEnum {
    /**
     * 线程池
     */
    THREADPOOL("threadPool", 0),
    /**
     * redis
     */
    REDIS("redis", 5),
    /**
     * redisson
     */
    REDISSON("redisson", 10);
    /**
     * 集成客户端名称
     */
    private String name;

    private int order;

    IntegrateOrderEnum(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

}
