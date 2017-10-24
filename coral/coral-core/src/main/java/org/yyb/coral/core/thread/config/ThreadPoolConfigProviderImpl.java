package org.yyb.coral.core.thread.config;

import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.utils.NumberUtils;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.configmodel.provider.AbstractConfigModelProvider;

/**
 * 线程池配置解析提供者
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午4:27:06
 *
 */
@SpiMeta(name = "threadpool")
public class ThreadPoolConfigProviderImpl extends AbstractConfigModelProvider {

	public static final String COREPOOLSIZE_KEY = "corepoolsize";
	public static final String MAXIMUMPOOLSIZE_KEY = "maximumpoolsize";
	public static final String KEEPALIVETIME_KEY = "keepalivetime";
	public static final String WORKQUEUESIZE_KEY = "workqueuesize";

	public ThreadPoolConfigProviderImpl() {
		super();
	}

	@Override
	protected String getConfigName() {
		return "threadpool";
	}

	@Override
	protected IConfigModel makeConfigModel(String configGroup) {
		ThreadPoolConfigModel configModel = new ThreadPoolConfigModel();
		// 配置ID为配置名-分组
		configModel.setId(getConfigName() + "-" + configGroup);

		//
		String corePoolSizeStr = getConfigValue(configGroup, COREPOOLSIZE_KEY);
		configModel.setCorePoolSize(NumberUtils.toInt(corePoolSizeStr, ThreadPoolConfigEnum.COREPOOLSIZE.defaultValue));

		String maximumPoolSizeStr = getConfigValue(configGroup, MAXIMUMPOOLSIZE_KEY);
		configModel.setMaximumPoolSize(
				NumberUtils.toInt(maximumPoolSizeStr, ThreadPoolConfigEnum.MAXINUMPOOLSIZE.defaultValue));

		String keepAliveTimeStr = getConfigValue(configGroup, KEEPALIVETIME_KEY);
		configModel.setKeepAliveTime(NumberUtils.toLong(keepAliveTimeStr,
				NumberUtils.toLong(String.valueOf(ThreadPoolConfigEnum.KEEPALIVETIME.defaultValue))));

		String workQueueSizeStr = getConfigValue(configGroup, WORKQUEUESIZE_KEY);
		configModel
				.setWorkQueueSize(NumberUtils.toInt(workQueueSizeStr, ThreadPoolConfigEnum.WORKQUEUESIZE.defaultValue));
		return configModel;
	}

	private static enum ThreadPoolConfigEnum {
		/**
		 * COREPOOLSIZE,默认8
		 */
		COREPOOLSIZE(8),
		/**
		 * 最大线程池数
		 */
		MAXINUMPOOLSIZE(-1),
		/**
		 * 超过核心线程数线程存活时间，毫秒
		 */
		KEEPALIVETIME(100),
		/**
		 * 队列大小
		 */
		WORKQUEUESIZE(100);
		private int defaultValue;

		ThreadPoolConfigEnum(int defaultValue) {
			this.defaultValue = defaultValue;
		}

	}
}
