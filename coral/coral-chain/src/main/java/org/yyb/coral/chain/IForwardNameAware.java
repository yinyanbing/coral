package org.yyb.coral.chain;

/**
 * 下节点跳转名的织入接口,
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午3:12:54
 */
public interface IForwardNameAware {
	/**
	 * 获取跳转节点名
	 * 
	 * @return
	 */
	public String getForwardName();

	/**
	 * 设置跳转节点名
	 * 
	 * @param forwardName
	 */
	public void setForwardName(String forwardName);
}
