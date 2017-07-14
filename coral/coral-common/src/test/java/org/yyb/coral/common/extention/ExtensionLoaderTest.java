package org.yyb.coral.common.extention;

import org.junit.Test;
import org.yyb.coral.common.extension.ExtensionLoader;
import org.yyb.coral.common.extension.Spi;
import org.yyb.coral.common.extention.ext1.ISpiExt1;
import org.yyb.coral.common.extention.ext1.ISpiExt2;
import org.yyb.coral.common.extention.ext1.impl.SpiExt1Impl;
import org.yyb.coral.common.extention.ext1.impl.SpiExt1Impl2;

import junit.framework.Assert;

public class ExtensionLoaderTest {
	@Test
	public void testExtensionNormal() {
		// 单例模式下只会构造一次实例
		Assert.assertEquals(1,
				ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SpiExt1Impl").spiHello());
		Assert.assertEquals(1,
				ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtension("SpiExt1Impl").spiHello());

		// 多例模式下在每次获取的时候进行实例化
		Assert.assertEquals(1,
				ExtensionLoader.getExtensionLoader(ISpiExt2.class).getExtension("SpiExt2Impl").spiHello());
		Assert.assertEquals(2,
				ExtensionLoader.getExtensionLoader(ISpiExt2.class).getExtension("SpiExt2Impl").spiHello());
		//
		// 手动添加实现类
		Assert.assertEquals(1, ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtensions("").size());
		ExtensionLoader loader = ExtensionLoader.getExtensionLoader(ISpiExt1.class);
		loader.addExtensionClass(SpiExt1Impl2.class);
		//
		// 返回所有实现类
		ExtensionLoader.initExtensionLoader(ISpiExt1.class);
		Assert.assertEquals(1, ExtensionLoader.getExtensionLoader(ISpiExt2.class).getExtensions("").size());
		Assert.assertEquals(2, ExtensionLoader.getExtensionLoader(ISpiExt1.class).getExtensions("").size());

	}

	@Test
	public void testExtensionAbNormal() {

		// 没有注解spi的接口无法进行扩展
		try {
			ExtensionLoader.getExtensionLoader(NotSpiInterface.class);
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("without @Spi annotation"));
		}

		// 非接口无法进行扩展
		try {
			ExtensionLoader.getExtensionLoader(SpiExt1Impl.class);
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("is not interface"));
		}

		Assert.assertNull(ExtensionLoader.getExtensionLoader(SpiWithoutImpl.class).getExtension("default"));
	}

	// not spi
	public interface NotSpiInterface {
	}

	// not impl
	@Spi
	public interface SpiWithoutImpl {
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
