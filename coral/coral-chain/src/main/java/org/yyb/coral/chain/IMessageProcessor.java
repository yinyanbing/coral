package org.yyb.coral.chain;

public interface IMessageProcessor {
	/**
	 * 链式消息处理
	 * 
	 * @param context
	 * @return 返回下一个链的处理名称
	 * @throws Exception
	 */
	public String process(MessageContext context) throws Exception;
}
