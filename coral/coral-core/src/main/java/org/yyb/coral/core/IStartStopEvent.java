package org.yyb.coral.core;

/**
 * 接口，一般用于启动、停止等
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午2:47:55
 */
public interface IStartStopEvent {
	/**
	 * 启动初始化
	 */
	public void start();

	/**
	 * 停止销毁
	 */
	public void stop();
}
