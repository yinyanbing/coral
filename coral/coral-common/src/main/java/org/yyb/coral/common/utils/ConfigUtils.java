package org.yyb.coral.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.Constants;
import org.yyb.coral.common.config.IConfig;
import org.yyb.coral.common.extension.ExtensionLoader;

/**
 * 通过SPI扩展获取配置参数信息
 * 
 * @ClassName: ConfigExtensionHelper
 * @author: yybg
 * @date: 2017年6月13日 下午3:11:20
 */
public class ConfigUtils {

	private ConfigUtils() {
	}

	/**
	 * 通过SPI配置名称、KEY获取配置值
	 * 
	 * @param spiConfigName
	 * @param key
	 * @return
	 */
	public static String getSpiConfigValue(String spiConfigName, String key) {
		IConfig config = ExtensionLoader.getExtensionLoader(IConfig.class)
				.getExtension(getSpiConfigName(spiConfigName));
		if (config != null) {
			return config.getConfigValue(key);
		}
		return null;
	}

	/**
	 * 通过SPI配置名称、KEY获取配置值,如果配置值为空则返回valueForNull
	 * 
	 * @param spiConfigName
	 * @param key
	 * @param valueForNull
	 *            参数值为空时返回的默认值
	 * @return
	 */
	public static String getSpiConfigValue(String spiConfigName, String key, String valueForNull) {
		IConfig config = ExtensionLoader.getExtensionLoader(IConfig.class)
				.getExtension(getSpiConfigName(spiConfigName));
		if (config != null) {
			return config.getConfigValue(key);
		}
		return null;
	}

	/**
	 * 通过SPI配置名称、模块、组、KEY获取参数值，通过{module}.{group}.{key}来获取配置值,
	 * 
	 * <pre>
	 * module=spring,group=datasource,key=username,则最终key为
	 * spring.datasource.username 获取配置值
	 * </pre>
	 * 
	 * @param spiConfigName
	 * @param module
	 * @param group
	 * @param key
	 * @return
	 */
	public static String getSpiConfigValue(String spiConfigName, String module, String group, String key) {
		IConfig config = ExtensionLoader.getExtensionLoader(IConfig.class)
				.getExtension(getSpiConfigName(spiConfigName));
		if (config != null) {
			return config.getConfigValue(module, group, key);
		}
		return null;
	}

	/**
	 * 通过SPI配置名称、模块、组、KEY获取参数值，通过{module}.{group}.{key}来获取配置值,
	 * 
	 * <pre>
	 * module=spring,group=datasource,key=username,则最终key为
	 * spring.datasource.username 获取配置值
	 * </pre>
	 * 
	 * 如果配置值为空则返回valueForNull
	 * 
	 * @param spiConfigName
	 * @param module
	 * @param group
	 * @param key
	 * @param valueForNull
	 *            配置值为空时返回的默认值
	 * @return
	 */
	public static String getSpiConfigValue(String spiConfigName, String module, String group, String key,
			String valueForNull) {
		IConfig config = ExtensionLoader.getExtensionLoader(IConfig.class)
				.getExtension(getSpiConfigName(spiConfigName));
		if (config != null) {
			return config.getConfigValue(module, group, key, valueForNull);
		}
		return null;
	}

	/**
	 * 通过KEY获取配置值
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String key) {
		return getSpiConfigValue(Constants.CORAL_SPI_CONFIG_ACTIVE, key);
	}

	/**
	 * 通过KEY获取配置值. 如果配置值为空则返回valueForNull
	 * 
	 * @param key
	 * @param valueForNull
	 *            配置值为空时返回的默认值
	 * @return
	 */
	public static String getConfigValue(String key, String valueForNull) {
		return getSpiConfigValue(Constants.CORAL_SPI_CONFIG_ACTIVE, key, valueForNull);

	}

	/**
	 * 通过模块、组、KEY获取参数值，通过{module}.{group}.{key}来获取配置值,
	 * 
	 * <pre>
	 * module=spring,group=datasource,key=username,则换换为
	 * spring.datasource.username 获取参数值
	 * </pre>
	 * 
	 * @param module
	 * @param group
	 * @param key
	 * @return
	 */
	public static String getConfigValue(String module, String group, String key) {
		return getSpiConfigValue(Constants.CORAL_SPI_CONFIG_ACTIVE, module, group, key);
	}

	/**
	 * 通过模块、组、KEY获取参数值，通过{module}.{group}.{key}来获取配置值,
	 * 
	 * <pre>
	 * module=spring,group=datasource,key=username,则换换为
	 * spring.datasource.username 获取参数值
	 * </pre>
	 * 
	 * 如果配置值为空则返回valueForNull
	 * 
	 * @param module
	 * @param group
	 * @param key
	 * @param valueForNull
	 *            配置值为空时返回的默认值
	 * @return
	 */
	public static String getConfigValue(String module, String group, String key, String valueForNull) {
		return getSpiConfigValue(Constants.CORAL_SPI_CONFIG_ACTIVE, module, group, key, valueForNull);
	}

	/**
	 * 获取SPI配置名
	 * 
	 * @param spiConfigName
	 * @return
	 */
	private static String getSpiConfigName(String spiConfigName) {
		if (StringUtils.isNotBlank(spiConfigName)) {
			return spiConfigName;
		}
		return Constants.CORAL_SPI_CONFIG_ACTIVE;
	}
}
