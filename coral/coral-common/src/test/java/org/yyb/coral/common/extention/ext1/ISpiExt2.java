package org.yyb.coral.common.extention.ext1;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

@Spi(scope = Scope.PROTOTYPE)
public interface ISpiExt2 {
	long spiHello();
}
