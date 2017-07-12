package org.yyb.coral.common.extention.ext1.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.extention.ext1.ISpiExt1;

@SpiMeta(name = "SpiExt2Impl")
public class SpiExt2Impl implements ISpiExt1 {
	private static AtomicLong counter = new AtomicLong(0);
	private long index = 0;

	public SpiExt2Impl() {
		index = counter.incrementAndGet();
	}

	@Override
	public long spiHello() {
		return index;
	}

}
