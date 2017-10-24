package org.yyb.coral.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字工具类
 * 
 * @author: yybg
 * @date: 2017年10月9日 下午2:46:05
 */
public class NumberUtils {
	private NumberUtils() {
	}

	/**
	 * 获取字符串转int值,入参为空或转异常返回0
	 * 
	 * @param value
	 * @return
	 */
	public static int toInt(String value) {
		return toInt(value, 0);
	}

	/**
	 * 获取字符串转int值
	 * 
	 * @param value
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static int toInt(String value, int defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		try {
			int numValue = Integer.parseInt(value);
			return numValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取字符串转long值,入参为空或转异常返回0
	 * 
	 * @param value
	 * @return
	 */
	public static long toLong(String value) {
		return toLong(value, 0L);
	}

	/**
	 * 获取字符串转long 值
	 * 
	 * @param value
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static long toLong(String value, long defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		try {
			long numValue = Long.parseLong(value);
			return numValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取字符串转short值,入参为空或转异常返回0
	 * 
	 * @param value
	 * @return
	 */
	public static short toShort(String value) {
		return toShort(value, (short) 0);
	}

	/**
	 * 获取字符串转short值
	 * 
	 * @param value
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static short toShort(String value, short defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		try {
			short numValue = Short.parseShort(value);
			return numValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 获取字符串转byte值,入参为空或转异常返回0
	 * 
	 * @param value
	 * @return
	 */
	public static byte toByte(String value) {
		return toByte(value, (byte) 0);
	}

	/**
	 * 获取字符串转byte值
	 * 
	 * @param value
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static byte toByte(String value, byte defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		try {
			byte numValue = Byte.parseByte(value);
			return numValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
