package org.yyb.coral.chain.chains;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.yyb.coral.chain.IEventProcessor;
import org.yyb.coral.chain.MessageContext;

/**
 * 流程链式代理消息处理器，通过beanname从spring容器获取处理器并传入首处理链名,用于链式调用入口
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午4:54:31
 */
public class ChainProxyMessageProcessor implements IEventProcessor, BeanFactoryAware {

	// 流程主链，管理链名
	private String managerName;
	// 主流程链的首处理器节点名
	private String forwardName;
	// SpringBeanFactory，根据forwardName、managerName获取具体实现类
	private BeanFactory beanFactory = null;
	// 具体主流程链,一般为DefaultChainMessageProcessor
	private ChainMessageProcessor chainProcessor = null;

	public ChainProxyMessageProcessor(String managerName, String forwardName) {
		this.forwardName = forwardName;
		this.managerName = managerName;
	}

	public ChainProxyMessageProcessor(ChainMessageProcessor chainProcessor, String forwardName) {
		this.chainProcessor = chainProcessor;
		this.forwardName = forwardName;
	}

	@Override
	public String process(MessageContext context) throws Exception {
		if (chainProcessor == null) {
			chainProcessor = beanFactory.getBean(managerName, ChainMessageProcessor.class);
		}
		if (chainProcessor == null) {
		}
		chainProcessor.process(forwardName, context);
		// chainProcessor.doProcess(forwardName, context);
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}
}
