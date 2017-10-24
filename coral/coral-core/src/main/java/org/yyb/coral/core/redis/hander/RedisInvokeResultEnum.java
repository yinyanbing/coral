package org.yyb.coral.core.redis.hander;

/**
 * redis调用结果枚举
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午3:45:53
 *
 */
public enum RedisInvokeResultEnum {
	/**
	 * success
	 */
	SUCCESS(1, "success"),
	/**
	 * error
	 */
	ERROR(2, "error"),
	/**
	 * noredisprovider
	 */
	NOREDISPROVIDER(3, "没有redisprovider"),
	/**
	 * noredisclient
	 */
	NOREDISCLIENT(4, "没有redisclient");

	private Integer code;

	private String title;

	RedisInvokeResultEnum(int code, String title) {
		this.code = code;
		this.title = title;
	}

	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

}
