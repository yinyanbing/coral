package org.yyb.coral.chain.basic;

import org.yyb.coral.chain.BasicMessageProcessor;
import org.yyb.coral.chain.IForwardNameAwareProcessor;
import org.yyb.coral.chain.MessageContext;
import org.yyb.coral.chain.exception.CoralChainRuntimeException;

/**
 * 当前调用处理链节点能否处理及是否处理完成判断、异常处理等的抽象类 具体消息处理器一般要继承此类
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午5:09:36
 */
public abstract class UnsettledMessageProcessor extends BasicMessageProcessor implements IForwardNameAwareProcessor {
	// 异常跳转名
	private String exceptionForwardName = null;

	public void setExceptionForwardName(String exceptionForwardName) {
		this.exceptionForwardName = exceptionForwardName;
	}

	/**
	 * 能否处理
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean doCanProcess(MessageContext context) throws Exception;

	/**
	 * 是否已经处理完成
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected boolean doCanFinish(MessageContext context) throws Exception {
		return false;
	}

	/**
	 * 处理器处理
	 */
	@Override
	public final String process(MessageContext context) throws Exception {
		try {
			// 当前处理器能处理当前数据,一般都能处理
			if (!doCanProcess(context)) {
				// 当前链不能处理抛异常
				if (context.getContent() == null) {
					throw new CoralChainRuntimeException(
							"类" + getClass().getName() + "无法处理，forwardName：" + getForwardName());
				} else {
					throw new CoralChainRuntimeException("类" + getClass().getName() + "无法处理"
							+ context.getContent().getClass().getName() + "，forwardName：" + getForwardName());
				}
			}

			if (doCanFinish(context)) {
				// 如果已经处理接受则返回NULL，结束链式调用
				return null;
			} else {
				// 返回next调用链名
				if (getForwardName() != null) {
					System.out.println("##NEXT-STEP-ON##==== " + getForwardName());
				}
				return getForwardName();
			}
		} catch (Exception e) {
			// 没有配置异常处理链时，直接往上抛异常
			if (exceptionForwardName == null)
				throw e;
			// 异常时Origin设置为content保存
			context.setOrigin(context.getContent());
			// 将context设置为当前的异常,并跳转到异常处理链节点，如果配置的话
			context.setContent(e);
			return exceptionForwardName;
		}
	}

}
