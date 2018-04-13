package org.yyb.coral.chain;

/**
 * 消息处理器
 * 
 * @author yybg
 * @date 2018年4月11日 下午5:11:00
 */
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
