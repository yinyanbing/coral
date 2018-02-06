package org.yyb.coral.core;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * 启动停止 ，coral组合接口
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:48:14
 */
@Spi(scope = Scope.SINGLETON)
public interface IStartStop extends IStartStopInfo, IStartStopEvent, IOrdered {

}
