package org.yyb.coral.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.common.extension.SpiMeta;

/**
 * 默认实现，如果没有其它配置替换的话 默认是从classpath:coralconfig.properties获取
 * 
 * @ClassName: DefaultConfig
 * @author: yybg
 * @date: 2017年6月13日 下午12:57:53
 */
@SpiMeta(name = "default")
public class DefaultConfig extends AbstractConfig {

	private static Logger logger = LoggerFactory.getLogger(DefaultConfig.class);

	private Properties properties = new Properties();;

	private volatile boolean hasLoad = false;

	private volatile boolean loadSuccess = false;

	@Override
	public String getConfigValue(String key) {
		check();
		return properties.getProperty(key);
	}

	@Override
	public String getConfigValue(String module, String group, String key) {
		if (StringUtils.isBlank(module) || StringUtils.isBlank(group) || StringUtils.isBlank(key)) {
			return null;
		}
		check();
		return properties.getProperty(getModuleGroupKey(module, group, key));
	}

	private String getModuleGroupKey(String module, String group, String key) {
		return module + "." + group + "." + key;
	}

	private void check() {
		if (!hasLoad && !loadSuccess) {
			loadPropertiesFromFile();
		}
	}

	private Properties loadPropertiesFromFile() {
		try {
			Properties prop = loadProperties(DEFAULT_PROPERTIES_FILE_NAME);
			if (prop != null) {
				properties = prop;
				loadSuccess = true;
			}
		} catch (Exception e) {
			logger.info("Load " + DEFAULT_PROPERTIES_FILE_NAME + " error!");
			logger.error("", e);
			loadSuccess = false;
		}
		hasLoad = true;
		return properties;
	}

	private Properties loadProperties(String location) {
		InputStream in = null;
		Properties property = null;
		try {
			property = new Properties();
			in = getResourceAsStream(location);
			if (in != null)
				property.load(in);
			else
				throw new FileNotFoundException("File[" + location + "]is not found!");
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return property;
	}

	private InputStream getResourceAsStream(String name) {
		if (name.startsWith("/")) {
			name = name.substring(1);
		}
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		return ccl.getResourceAsStream(name);
	}
}
