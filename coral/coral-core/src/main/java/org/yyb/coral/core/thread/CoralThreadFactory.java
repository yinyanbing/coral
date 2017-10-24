package org.yyb.coral.core.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CORAL统一的线程工厂
 * 
 * @author: yybg
 * @date: 2017年10月23日 下午5:21:23
 *
 */
public class CoralThreadFactory implements ThreadFactory {

	private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
	private final AtomicInteger threadNumber = new AtomicInteger(1);
	private final String namePrefix;

	public CoralThreadFactory(String prefix) {
		super();
		// CoralPool-1-eventbus-thread-11
		namePrefix = "coralPool-" + POOL_NUMBER.getAndIncrement() + "-" + prefix + "-thread-";
	}

	@Override
	public Thread newThread(Runnable r) {
		CoralThread thread = new CoralThread(r, namePrefix + threadNumber.getAndIncrement());
		return thread;

	}

}
