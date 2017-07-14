package org.yyb.coral.common.config;

import org.yyb.coral.common.extension.Spi;
import org.yyb.coral.common.extension.Scope;

@Spi(scope = Scope.SINGLETON)
public interface IConfig {

	// 默认属性加载配置文件，默认在class下
	public static final String DEFAULT_PROPERTIES_FILE_NAME = "coralconfig.properties";

	/**
	 * 通过KEY获取参数值
	 * 
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key);

	/**
	 * 通过KEY获取参数值，如果此值为空则返回valueForNull
	 * 
	 * @param key
	 * @param valueForNull
	 * @return
	 */
	public String getConfigValue(String key, String valueForNull);

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
	public String getConfigValue(String module, String group, String key);

	/**
	 * 通过模块、组、KEY获取参数值，通过{module}.{group}.{key}来获取配置值,
	 * 
	 * <pre>
	 * module=spring,group=datasource,key=username,则换换为
	 * spring.datasource.username 获取参数值
	 * </pre>
	 * 
	 * 如果此值为空则返回valueForNull
	 * 
	 * @param module
	 * @param group
	 * @param key
	 * @return
	 */
	public String getConfigValue(String module, String group, String key, String valueForNull);
}
