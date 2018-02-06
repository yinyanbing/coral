package org.yyb.coral.core.syncinvoke;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.yyb.coral.core.model.SystemErrors;
import org.yyb.coral.core.model.IResultModel;
import org.yyb.coral.core.model.ResultGeneralModel;

/**
 * 同步转异步调用hander
 * 
 * @author: yybg
 * @date: 2017年11月3日 上午11:21:37
 *
 */
public class SyncRequestHander implements ISyncRequestHander {
	private ISyncRequest syncRequest = null;
	private IResultModel resultModel = null;

	public SyncRequestHander(ISyncRequest syncRequest) {
		super();
		this.syncRequest = syncRequest;
	}

	private CountDownLatch downLatch = new CountDownLatch(1);

	public String getRequestId() {
		return syncRequest.getRequestId();
	}

	/**
	 * 同步等待，发送异步消息处理等
	 * 
	 * @param timeout
	 *            同步等待超时时间，单位毫秒
	 * @param asyncHander
	 *            异步处理器
	 */
	public <T> IResultModel<T> syncInvokeWait(long timeout, AsyncHander asyncHander) {
		if (timeout <= 0 || asyncHander == null) {
			throw new SyncInvokeRuntimeException("timeout or asyncHander can not be null!");
		}
		try {
			// 增加
			SyncRequestHanderManager.instance().addCallerSyncRequest(this);
			asyncHander.hander(syncRequest);
			// 当前线程等待，异步处理完成后调用asyncNotify 通知调用结束
			boolean waitResult = downLatch.await(timeout, TimeUnit.MILLISECONDS);
			if (waitResult) {
				// 接受到结果,
				return resultModel;
			} else {
				// 超时
				return new ResultGeneralModel<T>(SystemErrors.SYS_504);
			}
		} catch (InterruptedException e) {
			// 线程被Interrupted或其它原因线程
			return new ResultGeneralModel<T>(SystemErrors.SYS_500);
		}
	}

	/**
	 * 异步处理完成通知
	 */
	public <T> void asyncNotify(IResultModel<T> resultModel) {
		this.resultModel = resultModel;
		downLatch.countDown();
	}

}
