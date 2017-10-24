package org.yyb.coral.core.businessinit;

/**
 * 业务顺序初始化、销毁抽象类，具体业务初始化销毁需要实现
 * 
 * @author: yybg
 * @date: 2017年10月20日 上午10:09:31
 *
 */
public abstract class AbstractBusinessInit implements IBusinessInit {
	private String name = null;

	public AbstractBusinessInit(String name) {
		this.name = name;
		BusinessInitManager.instance().registerBean(this);
	}

	@Override
	public String getStartInfo() {
		return name;
	}

	@Override
	public String getStopInfo() {
		return name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void start() {
		startInternal();
	}

	@Override
	public void stop() {
		stopInternal();
	}

	/**
	 * 启动内部实现抽象
	 */
	abstract protected void startInternal();

	/**
	 * 停止内部实现抽象
	 */
	abstract protected void stopInternal();

}
