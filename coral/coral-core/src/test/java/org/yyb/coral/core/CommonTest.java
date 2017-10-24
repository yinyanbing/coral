package org.yyb.coral.core;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * JUnit4使用Java5中的注解（annotation），以下是JUnit4常用的几个annotation：
 * 
 * @Before：初始化方法 对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
 * @After：释放资源 对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）
 * @Test：测试方法，在这里可以测试期望异常和超时时间
 * @Test(expected=ArithmeticException.class)检查被测方法是否抛出ArithmeticException异常
 * @Ignore：忽略的测试方法
 * @BeforeClass：针对所有测试，只执行一次，且必须为static void
 * @AfterClass：针对所有测试，只执行一次，且必须为static void
 * 
 * @author: yybg
 * @date: 2017年10月20日 上午11:09:30
 *
 */
public class CommonTest {

	@BeforeClass
	public static void before() {
		CoralConfig config = new CoralConfig();
		CoralApplication.start(config);
	}

	@AfterClass
	public static void after() throws InterruptedException {
		CoralApplication.stop();
		TimeUnit.SECONDS.sleep(5);
	}

}
