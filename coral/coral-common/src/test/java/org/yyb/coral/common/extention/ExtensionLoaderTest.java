package org.yyb.coral.common.extention;

import org.junit.Test;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.common.extention.ext1.ISpiExt1;

import junit.framework.Assert;

public class ExtensionLoaderTest {
	@Test
	public void testExtensionNormal() {
		// 单例模式下只会构造一次实例
		Assert.assertEquals(1,
				ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SpiExt1Impl").spiHello());
		Assert.assertEquals(1,
				ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SpiExt1Impl").spiHello());

		// // 多例模式下在每次获取的时候进行实例化
		// Assert.assertEquals(1,
		// ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class).getExtension("spiPrototypeTest").spiHello());
		// Assert.assertEquals(2,
		// ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class).getExtension("spiPrototypeTest").spiHello());
		//
		// // 手动添加实现类
		// Assert.assertEquals(1,
		// ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class).getExtensions("").size());
		// ExtensionLoader loader =
		// ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class);
		// loader.addExtensionClass(SpiPrototypeTestImpl2.class);
		//
		// // 返回所有实现类
		// ExtensionLoader.initExtensionLoader(SpiPrototypeInterface.class);
		// Assert.assertEquals(1,
		// ExtensionLoader.getExtensionLoader(SpiTestInterface.class).getExtensions("").size());
		// Assert.assertEquals(2,
		// ExtensionLoader.getExtensionLoader(SpiPrototypeInterface.class).getExtensions("").size());

	}
	//
	// @Test
	// public void test_getExtension() throws Exception {
	// ISpiExt1 ext1 =
	// ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SimpleExtImpl1");
	// if (ext1 instanceof SpiExt1Impl) {
	// System.out.println("true");
	// }
	// }
	//
	// @Test
	// public void test_singletonExtension() throws Exception {
	// ISpiExt1 ext1 =
	// ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SimpleExtImpl1");
	// ISpiExt1 ext11 =
	// ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SimpleExtImpl1");
	// assertArrayEquals(new Object[] { ext1 }, new Object[] { ext11 });
	// }
}
