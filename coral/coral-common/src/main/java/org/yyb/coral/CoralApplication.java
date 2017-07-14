package org.yyb.coral;

import org.apache.commons.lang3.StringUtils;

/**
 * CORAL启动初始化及设置
 * 
 * @author: yybg
 * @date: 2017年7月14日 下午4:57:50
 */
public class CoralApplication {

	/**
	 * 设置CORAL默认的配置SPI实现
	 * 
	 * @param spiConfigName
	 */
	public static void setSpiConfigActive(String spiConfigName) {
		if (StringUtils.isNotBlank(spiConfigName)) {
			Constants.CORAL_SPI_CONFIG_ACTIVE = spiConfigName;
		}
	}

	/**
	 * 获取CORAL默认的配置SPI实现
	 * 
	 * @return
	 */
	public static String getSpiConfigActive() {
		return Constants.CORAL_SPI_CONFIG_ACTIVE;
	}

	private CoralApplication() {
	}
}
