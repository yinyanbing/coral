package org.yyb.coral.core.businessinit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.yyb.coral.core.CommonTest;

/**
 * 业务初始化测试
 * 
 * @author: yybg
 * @date: 2017年10月20日 下午1:28:38
 *
 */
public class BusinessInitTest extends CommonTest {
	private Binit01 binitOne = new Binit01();
	private Binit02 binitTwo = new Binit02();

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
			assertEquals(true, true);
			System.out.println("Binit01   start***************************");
		}

		@Override
		protected void stopInternal() {
			assertEquals(true, true);
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
