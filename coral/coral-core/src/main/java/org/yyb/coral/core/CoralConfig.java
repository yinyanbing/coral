package org.yyb.coral.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * coral核心配置。可配置提供的核心组件实现等
 * 
 * @author: yybg
 * @date: 2017年10月19日 上午9:30:30
 *
 */
public class CoralConfig {
	/**
	 * coral核心组件信息
	 */
	private static CopyOnWriteArrayList<CompomentInfo> compomentInfos = new CopyOnWriteArrayList<CompomentInfo>();
	/**
	 * coral核心组件信息,key:组件名
	 */
	private static ConcurrentHashMap<String, CompomentInfo> compomentInfoMaps = new ConcurrentHashMap<String, CompomentInfo>();

	static {
		for (CoralCompomentsEnum compomentsEnum : CoralCompomentsEnum.values()) {
			CompomentInfo compomentInfo = new CompomentInfo(compomentsEnum.getName(), compomentsEnum.getProviderName());
			compomentInfos.add(compomentInfo);
			compomentInfoMaps.put(compomentsEnum.getName(), compomentInfo);
		}

	}

	public void addCompoment(String comName, String providerName) {
		if (StringUtils.isBlank(comName) || StringUtils.isBlank(providerName)) {
			return;
		}
		if (!compomentInfoMaps.containsKey(comName)) {
			CompomentInfo compomentInfo = new CompomentInfo(comName, providerName);
			compomentInfos.add(compomentInfo);
			compomentInfoMaps.put(comName, compomentInfo);
		}

	}

	public CompomentInfo getCompoment(String name) {
		return compomentInfoMaps.get(name);
	}
}
