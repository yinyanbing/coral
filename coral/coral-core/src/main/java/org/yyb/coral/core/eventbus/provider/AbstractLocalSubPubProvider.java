package org.yyb.coral.core.eventbus.provider;

import org.yyb.coral.core.ICoralName;
import org.yyb.coral.core.eventbus.IApplicationEvent;

/**
 * 远程订阅发布实现抽象类
 * 
 * @author: yybg
 * @date: 2017年10月16日 下午4:18:20
 */
public abstract class AbstractLocalSubPubProvider implements ILocalSubPubProvider, ICoralName {
    /**
     * 本地订阅发布的实现名称，如guava、spring等
     */
    private String name = null;

    public AbstractLocalSubPubProvider(String name) {
        super();
        this.name = name;
        start();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 1:启动线程订阅监听
     */
    @Override
    public void start() {
        startInternal();
    }

    @Override
    public void stop() {
        stopInternal();
    }

    @Override
    public boolean publishEvent(IApplicationEvent appEvent) {
        return publishEventInternal(appEvent);
    }

    /**
     * 发布事件具体子类实现
     * 
     * @param appEvent
     * @return
     */
    protected abstract boolean publishEventInternal(IApplicationEvent appEvent);

    /**
     * 子类实现启动处理
     */
    protected abstract void startInternal();

    /**
     * 子类实现停止销毁处理
     */
    protected abstract void stopInternal();

}
