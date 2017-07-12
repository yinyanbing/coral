package org.yyb.coral.common.utils;

import org.yyb.coral.common.Constants;

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

	private BaseUtils() {
	}
}
