package org.yyb.coral;

import java.util.UUID;

/**
 * 常量类
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:20:49
 */
public class Constants {
	public static final String CORAL = "coral";
	// 配置源默认SPI实现,不同平台配置不同时初始化可以修改为对应SPI实现
	public static String CORAL_SPI_CONFIG_ACTIVE = "default";
	// utf-8
	public static final String CHARSET_UTF = "UTF-8";
	// 日志前缀
	public static final String LOG_PREFIX = "*****Coral: ";

	public static final String CORAL_DEFAULT = "default";
	// 配置属性分割符号
	public static final String CONFIG_PROPERTIES_DIVIDED = ".";
	// redis
	public static final String CORAL_REDIS = "redis";
	/**
	 * 线程池
	 */
	public static final String CORAL_THREADPOOL = "threadpool";

	// 每次JVM启动唯一应用标识
	public static final String SP_APPLICATIONID = UUID.randomUUID().toString().replace("-", "");

	// 支撑统一的redis事件总线channel
	public static final String CORAL_EVENTBUS_CHANNEL = "CoralEventbusChannel";
	// 默认的事件发布者
	public static final String CORAL_EVENTBUS_EVENTPUBLISHER = "defaultEventPublisher";

}
