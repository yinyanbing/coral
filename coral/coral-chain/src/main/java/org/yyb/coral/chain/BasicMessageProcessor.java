package org.yyb.coral.chain;

/**
 * 基础消息处理抽象类
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午2:55:19
 */
public abstract class BasicMessageProcessor implements IMessageProcessor, IProcessorEvent {
	// 跳转名。 下一个链式节点名称，通过当前节点返回的下节点名称判断是否有后续节点需要执行
	private String forwardName;

	public void setForwardName(String forwardName) {
		if (forwardName != null) {
			System.out.println(":::::FORWARD_NAME::::: " + forwardName);
		}
		this.forwardName = forwardName;
	}

	public String getForwardName() {
		return forwardName;
	}

	/**
	 * 空实现，具体工作由具体执行子类实现
	 */
	public void start() {

	}

	/**
	 * 空实现，具体工作由具体执行子类实现
	 */
	public void stop() {

	}
}
