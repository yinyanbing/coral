package org.yyb.coral.chain;

/**
 * 处理事件接口，一般用于启动、停止等
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午2:47:55
 */
public interface IProcessorEvent {
	public void start();

	public void stop();
}
