package org.yyb.coral.common.config;

import org.junit.Test;
import org.yyb.coral.common.utils.ConfigUtils;

import junit.framework.Assert;

public class ConfigTest {
	@Test
	public void testSpiConfigDefault() {
		Assert.assertEquals("tname", ConfigUtils.getConfigValue("test.name"));
	}
}
