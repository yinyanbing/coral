package org.yyb.coral.core.eventbus.provider;

import org.yyb.coral.Constants;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.core.ICoralName;
import org.yyb.coral.core.eventbus.IApplicationEvent;
import org.yyb.coral.core.thread.IThreadPoolProvider;
import org.yyb.coral.core.thread.ThreadPoolProviderFactory;

import com.google.common.util.concurrent.ListeningExecutorService;

/**
 * 远程订阅发布实现抽象类
 * 
 * @author: yybg
 * @date: 2017年10月16日 下午4:18:20
 *
 */
public abstract class AbstractRemoteSubPubProvider implements IRemoteSubPubProvider, ICoralName {
	/**
	 * 订阅发布的实现名称，如redis、RocketMQ等
	 */
	private String name = null;

	public AbstractRemoteSubPubProvider(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void start() {
		Thread thread = new Thread(getSubscriberWorker(), Constants.LOG_PREFIX + name + "-Remote subscribe thread");
		thread.start();
		startInternal();
	}

	@Override
	public void stop() {
		stopInternal();
	}

	@Override
	public boolean publishEvent(IApplicationEvent appEvent) {
		return publishEventInternal(appEvent);
	}

	/**
	 * 订阅具体实现，可继承AbstarctSubscriberWorker
	 * 
	 * @return
	 */
	protected abstract Runnable getSubscriberWorker();

	/**
	 * 子类发布事件实现
	 * 
	 * @param appEvent
	 * @return
	 */
	protected abstract boolean publishEventInternal(IApplicationEvent appEvent);

	/**
	 * 子类实现启动处理
	 */
	protected abstract void startInternal();

	/**
	 * 子类实现停止销毁处理
	 */
	protected abstract void stopInternal();
}
