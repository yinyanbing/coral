package org.yyb.coral.core.eventbus.provider;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * 本地订阅发布提供者，本JVM发布事件提供者，默认GUAVA，后续可以spring事件等
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:58:48
 */
@Spi(scope = Scope.SINGLETON)
public interface ILocalSubPubProvider extends ISubPubProvider {
    /**
     * 注册监听
     * 
     * @param obj
     */
    public void registerListener(Object obj);
}
