package org.yyb.coral.common.utils;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.Constants;

/**
 * 基础工具类
 * 
 * @author: yybg
 * @date: 2017年10月19日 上午10:31:31
 *
 */
public class BaseUtils {
	/**
	 * 获取coral日志前缀
	 * 
	 * @param inlogtext
	 * @param params
	 * @return
	 */
	public static String getLogText(String inlogtext, Object... params) {
		return Constants.LOG_PREFIX + String.format(inlogtext, params);
	}

	/**
	 * 获取配置的属性key。
	 * <p>
	 * coral.redis.host; coral.redis.test.host
	 * </p>
	 * 
	 * @param prefix
	 *            前缀，如redis
	 * @param group
	 *            分组，default的话忽略
	 * @param key
	 *            key
	 * @return
	 */
	public static String getPropertyKey(String prefix, String group, String key) {
		if (StringUtils.isBlank(prefix) || StringUtils.isBlank(group) || StringUtils.isBlank(key)) {
			return null;
		}
		if (Constants.CORAL_DEFAULT.equals(group)) {
			return Constants.CORAL + "." + prefix + "." + key;
		}
		return Constants.CORAL + "." + prefix + "." + group + "." + key;
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUIDText() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	private BaseUtils() {
	}
}
