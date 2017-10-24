package org.yyb.coral.core.configmodel;

import org.yyb.coral.common.extension.ExtensionLoader;

/**
 * 配置model工厂，提供默认及指定配置组的配置信息
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:48:39
 *
 */
public class ConfigModelProviderFactory {

	private ConfigModelProviderFactory() {
	}

	public static IConfigModel getDefaultConfigModel(String configName) {
		return ExtensionLoader.getExtensionLoader(IConfigModelProvider.class).getExtension(configName)
				.getDefaultConfigModel();
	}

	public static IConfigModel getConfigModel(String configName, String configGroup) {
		return ExtensionLoader.getExtensionLoader(IConfigModelProvider.class).getExtension(configName)
				.getConfigModel(configGroup);
	}
}
