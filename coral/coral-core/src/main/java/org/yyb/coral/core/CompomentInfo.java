package org.yyb.coral.core;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 组件信息，用于coral启动等设置
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:47:19
 *
 */
public class CompomentInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 451318697225360L;
	private String name = null;
	private String providerName = null;
	private Map<String, String> extInfon = null;

	public CompomentInfo(String name, String providerName) {
		this.name = name;
		this.providerName = providerName;
		extInfon = new ConcurrentHashMap<String, String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getExtValue(String extkey) {
		return extInfon.get(extkey);
	}

	public void setExtValue(String extkey, String extValue) {
		extInfon.put(extkey, extValue);
	}
}
