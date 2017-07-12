package org.yyb.coral.common.extention.ext1;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * 扩展点
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午5:26:58
 */
@Spi(scope = Scope.SINGLETON)
public interface ISpiExt1 {

	long spiHello();

}