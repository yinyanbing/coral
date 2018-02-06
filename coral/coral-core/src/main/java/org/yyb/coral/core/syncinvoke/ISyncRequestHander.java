package org.yyb.coral.core.syncinvoke;

import org.yyb.coral.core.model.IResultModel;

/**
 * 同步转异步请求hander
 * 
 * @author: yybg
 * @date: 2017年11月3日 上午11:23:28
 *
 */
public interface ISyncRequestHander {
	/**
	 * 获取请求ID
	 * 
	 * @return
	 */
	public String getRequestId();

	/**
	 * 同步调用等待
	 * 
	 * @param timeout
	 *            等待时间，单位毫秒
	 * @param asyncHander
	 *            异步处理hander，同步前要异步处理
	 */
	public <T> IResultModel<T> syncInvokeWait(long timeout, AsyncHander asyncHander);

	/**
	 * 异步调用通知，返回异步结果，同步等待结束，返回给调用放
	 * 
	 * @param resultModel
	 */
	public <T> void asyncNotify(IResultModel<T> resultModel);

	/**
	 * 异步处理hander
	 * 
	 * @author: yybg
	 * @date: 2017年11月3日 上午11:02:46
	 *
	 */
	public static interface AsyncHander {
		/**
		 * 异步处理hander，如异步发送分布式消息(需要ISyncRequest序列化)、其它异步处理等,需要将
		 * 
		 * @param syncRequest
		 */
		public void hander(ISyncRequest syncRequest);
	}
}
