package org.yyb.coral.chain.chains;

/**
 * 链式消息处理织入
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午4:29:51
 */
public interface IChainMessageProcessorAware {
	/**
	 * 设置链式处理器
	 * 
	 * @param processor
	 */
	public void setChainProcessor(ChainMessageProcessor processor);
}
