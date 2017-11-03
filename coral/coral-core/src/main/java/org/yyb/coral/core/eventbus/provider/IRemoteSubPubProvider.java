package org.yyb.coral.core.eventbus.provider;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * 订阅监听
 * 
 * @author: yybg
 * @date: 2017年10月16日 上午11:01:13
 */
@Spi(scope = Scope.SINGLETON)
public interface IRemoteSubPubProvider extends ISubPubProvider {

}
