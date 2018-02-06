package org.yyb.coral.core.configmodel;

import org.yyb.coral.common.extension.Scope;
import org.yyb.coral.common.extension.Spi;

/**
 * 配置信息model提供者接口
 * 
 * @author: yybg
 * @date: 2017年10月9日 下午2:18:42
 */
@Spi(scope = Scope.SINGLETON)
public interface IConfigModelProvider {
    /**
     * 获取默认的配置model
     * 
     * @return
     */
    public IConfigModel getDefaultConfigModel();

    /**
     * 获取指定组的配置model，如redis配置，可能有多个组用于不同业务场景
     * 
     * @param configGroup
     * @return
     */
    public IConfigModel getConfigModel(String configGroup);
}
