package org.yyb.coral.core.provider;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yyb.coral.Constants;
import org.yyb.coral.common.exception.CoralRuntimeException;
import org.yyb.coral.core.IStartStop;
import org.yyb.coral.core.configmodel.ConfigModelProviderFactory;
import org.yyb.coral.core.configmodel.IConfigModel;

/**
 * 
 * 三方集成客户端实现抽象类
 * 
 * @author: yybg
 * @date: 2017年10月11日 下午2:47:22
 *
 */
public abstract class AbstractIntegrateClientProvider<T> implements IIntegrateClientProvider<T>, IStartStop {
	private static final Logger logger = LoggerFactory.getLogger(AbstractIntegrateClientProvider.class);

	/**
	 * 指定三方配置所有组初始化,key:configGroup
	 */
	private ConcurrentMap<String, ThirdPartyClientPoolIniter> clientPoolIniters = new ConcurrentHashMap<String, ThirdPartyClientPoolIniter>();

	/**
	 * 配置名，相当与某种中间件三方集成种类名，如redis,rocketMQ
	 */
	private String configName;

	/**
	 * 三方客户端名称，如jedis、redisson、rocketMQ;同一个config名可能有多个客户端，如jedis、redisson等
	 */
	private String thirdPartyName;

	private String thirdPartyClientName = "";

	public AbstractIntegrateClientProvider(String configName, String thirdPartyName) {
		this.configName = configName;
		this.thirdPartyName = thirdPartyName;
		if (StringUtils.isBlank(this.configName)) {
			throw new CoralRuntimeException("configName can not be null!");
		}
		if (StringUtils.isBlank(this.thirdPartyName)) {
			throw new CoralRuntimeException(configName + ":thirdPartyName can not be null!");
		}
		thirdPartyClientName = configName + ":" + thirdPartyName;
		initClientPool(Constants.CORAL_DEFAULT);
		// 注册启动初始化
		IntegrateClientStartStopManager.instance().registerBean(this);
	}

	/**
	 * 初始化默认的clientPool
	 */
	private void initClientPool(String configGroup) {
		ThirdPartyClientPoolIniter configGroupIniter = new ThirdPartyClientPoolIniter(thirdPartyClientName, configName,
				configGroup, new IClientPoolHander() {

					@Override
					public Object initClientPool(IConfigModel configModel) {
						return doMakeClientPoolInternal(configModel);
					}

					@Override
					public void destroyClientPool(Object clientPool) {
						doDestroyClientPoolInternal(clientPool);
					}
				});
		clientPoolIniters.putIfAbsent(configGroup, configGroupIniter);
		Object clientPool = clientPoolIniters.get(configGroup).getClientPool();
		if (clientPool == null) {
			logger.warn("There is no 【" + thirdPartyClientName + ":" + configGroup + "】clientPool!");
		}
	}

	@Override
	public T getClientDefault() {
		return getClient(Constants.CORAL_DEFAULT);
	}

	@Override
	public T getClient(String configGroup) {
		T client = getClientInternal(configGroup);
		if (client != null) {
			return client;
		}
		if (StringUtils.isNotBlank(configGroup) && !clientPoolIniters.containsKey(configGroup)
				&& !Constants.CORAL_DEFAULT.equals(configGroup)) {
			// 其它某项(redis)配置组连接池初始化
			initClientPool(configGroup);
			// 有可能还是为空，如缺失配置异常，初始化client有问题
			return getClientInternal(configGroup);
		}
		return null;
	}

	private T getClientInternal(String configGroup) {
		ThirdPartyClientPoolIniter configGroupIniter = clientPoolIniters.get(configGroup);
		if (configGroupIniter != null && configGroupIniter.getClientPool() != null) {
			return doGetClientInternal(configGroupIniter.getClientPool());
		}
		return null;
	}

	/**
	 * 具体客户端实现
	 * 
	 * @param configModel
	 * @return
	 */
	protected abstract Object doMakeClientPoolInternal(IConfigModel configModel);

	/**
	 * 销毁客户端池
	 * 
	 * @param clientPool
	 */
	protected abstract void doDestroyClientPoolInternal(Object clientPool);

	/**
	 * 获取集成客户端，有些是初始化客户端池，需要具体实现
	 * 
	 * @param clientPool
	 * @return
	 */
	protected abstract T doGetClientInternal(Object clientPool);

	/**
	 * 客户端池hander
	 * 
	 * @author: yybg
	 * @date: 2017年10月24日 下午1:41:53
	 *
	 */
	private static interface IClientPoolHander {
		/**
		 * 具体初始化客户端池调用
		 * 
		 * @param configModel
		 * @return
		 */
		public Object initClientPool(IConfigModel configModel);

		/**
		 * 客户端池销毁
		 * 
		 * @param clientPool
		 */
		public void destroyClientPool(Object clientPool);
	}

	/**
	 * 不需要自动启动一般，在调用三方集成时立即初始化
	 */
	@Override
	public void start() {
	}

	/**
	 * 停止，初始化了那些configGroup一般需要stop，如线程池、redisson等的关闭
	 */
	@Override
	public void stop() {
		Set<String> configGroups = clientPoolIniters.keySet();
		for (String configGroup : configGroups) {
			ThirdPartyClientPoolIniter clientPoolIniter = clientPoolIniters.get(configGroup);
			clientPoolIniter.destroyClientPool();
		}
	}

	@Override
	public String getStartInfo() {
		return thirdPartyClientName;
	}

	@Override
	public String getStopInfo() {
		return thirdPartyClientName;
	}

	@Override
	public String getName() {
		return thirdPartyClientName;
	}

	private static class ThirdPartyClientPoolIniter {
		private String configName = null;
		private String thirdPartyClientName = null;
		private String configGroup = null;
		private volatile Object client = null;
		private volatile boolean hasInited = false;
		private IClientPoolHander clientHander = null;

		public ThirdPartyClientPoolIniter(String thirdPartyClientName, String configName, String configGroup,
				IClientPoolHander clientHander) {
			this.thirdPartyClientName = thirdPartyClientName;
			this.configName = configName;
			this.configGroup = configGroup;
			this.clientHander = clientHander;
		}

		public Object getClientPool() {
			if (client != null) {
				return client;
			}
			if (!hasInited) {
				synchronized (this) {
					if (client != null) {
						return client;
					}
					init();
					return client;
				}
			}
			return null;
		}

		public void destroyClientPool() {
			try {
				clientHander.destroyClientPool(client);
			} catch (Exception e) {
				logger.error("destroy 【" + thirdPartyClientName + ":" + configGroup + "】   clientPool error!", e);
			}
		}

		private void init() {
			try {
				IConfigModel configModel = ConfigModelProviderFactory.getConfigModel(configName, configGroup);
				if (configModel != null) {
					client = clientHander.initClientPool(configModel);
				} else {
					logger.warn("There is no 【" + thirdPartyClientName + ":" + configGroup + "】  configModel!");
				}
			} catch (Exception e) {
				logger.error("init 【" + thirdPartyClientName + ":" + configGroup + "】   clientPool error!", e);
			}
			hasInited = true;
		}
	}
}
