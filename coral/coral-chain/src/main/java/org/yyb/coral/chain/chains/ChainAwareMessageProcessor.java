package org.yyb.coral.chain.chains;

import org.yyb.coral.chain.basic.UnsettledMessageProcessor;

/**
 * 链式消息处理器
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午5:24:45
 */
public abstract class ChainAwareMessageProcessor extends UnsettledMessageProcessor
		implements IChainMessageProcessorAwareProcessor {
	private ChainMessageProcessor processor;

	public final ChainMessageProcessor getChainProcessor() {
		return processor;
	}

	protected void onChainProcessorChanging(ChainMessageProcessor oldProcessor, ChainMessageProcessor newProcessor) {
	}

	public final void setChainProcessor(ChainMessageProcessor processor) {
		onChainProcessorChanging(this.processor, processor);
		this.processor = processor;
	}
}
