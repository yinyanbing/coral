package org.yyb.coral.core.thread;

import org.apache.commons.lang3.StringUtils;

/**
 * 线程池初始化方式
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午5:28:09
 *
 */
public enum ThreadInitTypeEnum {
	/**
	 * 普通线程池
	 */
	NORMAL("normal", "普通线程池"),
	/**
	 * 集群模式
	 */
	SCHEDULED("Scheduled", "集群模式");

	private String code;

	private String title;

	ThreadInitTypeEnum(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static ThreadInitTypeEnum getInitType(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (ThreadInitTypeEnum enumeData : ThreadInitTypeEnum.values()) {
				if (enumeData.getCode().equalsIgnoreCase(code)) {
					return enumeData;
				}
			}
		}
		return ThreadInitTypeEnum.NORMAL;
	}
}
