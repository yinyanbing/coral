package org.yyb.coral.core.businessinit;

import org.yyb.coral.core.IOrdered;
import org.yyb.coral.core.IStartStopEvent;
import org.yyb.coral.core.IStartStopInfo;

/**
 * 业务启动初始化及销毁
 * 
 * @author: yybg
 * @date: 2017年10月19日 上午9:37:29
 */
public interface IBusinessInit extends IStartStopInfo, IStartStopEvent, IOrdered {

}
