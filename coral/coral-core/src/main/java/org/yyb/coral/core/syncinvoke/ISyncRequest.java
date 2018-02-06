package org.yyb.coral.core.syncinvoke;

import java.util.Map;

/**
 * 同步请求
 * 
 * @author: yybg
 * @date: 2017年11月3日 上午11:22:24
 *
 */
public interface ISyncRequest {
	/**
	 * 获取请求ID
	 * 
	 * @return
	 */
	public String getRequestId();

	/**
	 * 获取请求参数
	 * 
	 * @return
	 */
	public Map<String, Object> getRequestData();

	/**
	 * 设置请求参数
	 * 
	 * @param requestData
	 */
	public void setRequestData(Map<String, Object> requestData);
}
