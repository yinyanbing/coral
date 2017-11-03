package org.yyb.coral.core.thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yyb.coral.core.CommonTest;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralConfig;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.RateLimiter;

/**
 * 线程池测试
 * 
 * @author: yybg
 * @date: 2017年10月21日 下午9:18:37
 *
 */
public class ThreadTest extends CommonTest {

	@Test
	public void testThreadpoolDefault() {
		IThreadPoolProvider threadPoolProvider = ThreadPoolProviderFactory.getDefaultThreadPoolProvider();
		ListeningExecutorService listeningExecutorService = threadPoolProvider.getExecutorServiceDefault();
		assertNotNull(listeningExecutorService);
	}

	@Test
	public void testThreadpoolRunResult() throws InterruptedException {
		IThreadPoolProvider threadPoolProvider = ThreadPoolProviderFactory.getDefaultThreadPoolProvider();
		ListeningExecutorService listeningExecutorService = threadPoolProvider.getExecutorServiceDefault();
		assertNotNull(listeningExecutorService);
		ListenableFuture<String> result = listeningExecutorService.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("over!!!!111111111111");
				return "over";
			}
		});

		result.addListener(new Runnable() {

			@Override
			public void run() {
				try {
					// // 每秒不超过4个任务被提交
					RateLimiter limiter = RateLimiter.create(4.0);
					// 请求RateLimiter, 超过permits会被阻塞
					limiter.acquire();
					// 异步获取
					String runResult = result.get();
					assertEquals("over", runResult);
					System.out.println("over!!!!2222222222");
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} catch (ExecutionException e) {
					fail(e.getMessage());
				}

			}
		}, listeningExecutorService);

		TimeUnit.SECONDS.sleep(5);
		System.out.println("over!!!!333333333333");

	}

}
