package org.yyb.coral.common.config;

import org.apache.commons.lang3.StringUtils;

/**
 * 获取配置参数
 * 
 * @ClassName: AbstractConfig
 * @author: yybg
 * @date: 2017年6月13日 下午1:20:46
 */
public abstract class AbstractConfig implements IConfig {

	@Override
	public String getConfigValue(String key, String valueForNull) {
		String cValue = getConfigValue(key);
		if (StringUtils.isBlank(cValue)) {
			return valueForNull;
		}
		return cValue;
	}

	@Override
	public String getConfigValue(String module, String group, String key, String valueForNull) {
		String cValue = getConfigValue(module, group, key);
		if (StringUtils.isBlank(cValue)) {
			return valueForNull;
		}
		return cValue;
	}

}
