package org.yyb.coral.core.syncinvoke;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 同步请求转异步管理
 * 
 * @author: yybg
 * @date: 2017年11月3日 上午10:43:24
 *
 */
public class SyncRequestHanderManager {
	private static final SyncRequestHanderManager INSTANCE = new SyncRequestHanderManager();
	/**
	 * 本JVM应有所有的同步请求
	 */
	private ConcurrentHashMap<String, SyncRequestHander> requests = new ConcurrentHashMap<String, SyncRequestHander>();

	public static SyncRequestHanderManager instance() {
		return INSTANCE;
	}

	public void addCallerSyncRequest(SyncRequestHander callerSyncRequest) {
		if (callerSyncRequest != null) {
			requests.putIfAbsent(callerSyncRequest.getRequestId(), callerSyncRequest);
		}
	}

	public void removeCallerSyncRequest(String requestId) {
		requests.remove(requestId);
	}

	public SyncRequestHander getSyncRequestHander(String requestId) {
		return requests.get(requestId);
	}

	private SyncRequestHanderManager() {
	}
}
