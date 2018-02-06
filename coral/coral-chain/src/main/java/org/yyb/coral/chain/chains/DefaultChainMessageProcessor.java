package org.yyb.coral.chain.chains;

import java.util.Map;

import org.yyb.coral.chain.IMessageProcessor;
import org.yyb.coral.chain.IProcessorEvent;
import org.yyb.coral.core.IStartStopInfo;

/**
 * 某个链式消息处理配置链，通过spring配置多个链式处理顺序执行。可以通过ChainProxyMessageProcessor代理包装并发布OSGI服务
 * 
 * <pre>
 * 可理解为定义的某一个流程链模版，每一个MessageProcessor为一个处理节点,通过spring的配置组织流程
 * </pre>
 * 
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午4:26:30
 */
public class DefaultChainMessageProcessor extends ChainMessageProcessor {
	/**
	 * 当前执行链配置的所有节点
	 */
	private Map<String, IMessageProcessor> processors;

	public DefaultChainMessageProcessor(Map<String, IMessageProcessor> processors) {
		this(processors, null);
	}

	public DefaultChainMessageProcessor(Map<String, IMessageProcessor> processors, String defaultName) {
		super(defaultName);
		this.processors = processors;
	}

	@Override
	protected IMessageProcessor doGetProcessor(String name) throws Exception {
		return processors.get(name);
	}

	/**
	 * 针对配置的具体MessageProcessor启动
	 */
	@Override
	public void start() {
		super.start();
		for (IMessageProcessor p : processors.values()) {
			if (p instanceof IChainMessageProcessorAware) {
				// 设置当前流程链为链式处理器，某一流程链
				((IChainMessageProcessorAware) p).setChainProcessor(this);
				System.out.println(
						"###ChainMessageProcessor###=== " + ((IChainMessageProcessorAware) p).getClass().getName());
			}
			if (p instanceof IStartStopInfo) {
				System.out.println("###ChainMessageProcessor###=== startInfo:" + ((IStartStopInfo) p).getStartInfo());
			}
			if (p instanceof IProcessorEvent) {
				((IProcessorEvent) p).start();
			}

		}
	}

	@Override
	public void stop() {
		super.stop();
		for (IMessageProcessor p : processors.values()) {
			if (p instanceof IChainMessageProcessorAware) {
				((IChainMessageProcessorAware) p).setChainProcessor(null);
			}
			if (p instanceof IProcessorEvent) {
				((IProcessorEvent) p).stop();
			}
			if (p instanceof IStartStopInfo) {
				System.out.println("###ChainMessageProcessor###=== stopInfo:" + ((IStartStopInfo) p).getStopInfo());
			}
		}
	}
}
