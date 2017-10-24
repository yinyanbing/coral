package org.yyb.coral.core.businessinit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yyb.coral.core.CoralApplication;
import org.yyb.coral.core.CoralConfig;

/**
 * 业务初始化测试
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午1:28:38
 *
 */
public class BusinessInitTest {

	@BeforeClass
	public static void before() {
		Binit01 binitOne = new Binit01();
		Binit02 binitTwo = new Binit02();
		CoralConfig config = new CoralConfig();
		CoralApplication.start(config);
	}

	@AfterClass
	public static void after() throws InterruptedException {
		CoralApplication.stop();
	}

	@Test
	public void testBusinessInit() {

	}

	private static class Binit01 extends AbstractBusinessInit {

		public Binit01() {
			super("Binit01");
		}

		@Override
		public int getOrder() {
			return 5;
		}

		@Override
		protected void startInternal() {
			System.out.println("Binit01   start***************************");
		}

		@Override
		protected void stopInternal() {
			System.out.println("Binit01   stop***************************");
		}

	}

	private static class Binit02 extends AbstractBusinessInit {

		public Binit02() {
			super("Binit02");
		}

		@Override
		public int getOrder() {
			return 10;
		}

		@Override
		protected void startInternal() {
			System.out.println("Binit02   start***************************");
		}

		@Override
		protected void stopInternal() {
			System.out.println("Binit02  stop***************************");
		}

	}
}
