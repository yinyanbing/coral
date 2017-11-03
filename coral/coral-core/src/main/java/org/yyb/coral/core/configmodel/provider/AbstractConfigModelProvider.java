package org.yyb.coral.core.configmodel.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.Constants;
import org.yyb.coral.common.exception.CoralRuntimeException;
import org.yyb.coral.common.utils.BaseUtils;
import org.yyb.coral.common.utils.ConfigUtils;
import org.yyb.coral.core.configmodel.ConfigModelRuntimeException;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.configmodel.IConfigModelProvider;

/**
 * 配置model提供者抽象实现类
 * 
 * @author: yybg
 * @date: 2017年10月9日 下午2:23:54
 */
public abstract class AbstractConfigModelProvider implements IConfigModelProvider {
    private Map<String, IConfigModel> configModelMap = new ConcurrentHashMap<String, IConfigModel>();

    /**
     * 配置名称，如redis，etcd等,某钟配置标识
     */
    protected String configName = null;

    public AbstractConfigModelProvider() {
        configName = getConfigName();
        if (StringUtils.isBlank(configName)) {
            throw new CoralRuntimeException("Config name can not be null!");
        }
    }

    @Override
    public IConfigModel getDefaultConfigModel() {
        return getConfigModel(Constants.CORAL_DEFAULT);
    }

    @Override
    public IConfigModel getConfigModel(String configGroup) {
        if (StringUtils.isBlank(configGroup)) {
            throw new ConfigModelRuntimeException("configGroup can not be null!");
        }
        IConfigModel configModel = configModelMap.get(configGroup);
        if (configModel != null) {
            return configModel;
        }
        configModel = makeConfigModel(configGroup);
        if (configModel != null) {
            configModelMap.putIfAbsent(configGroup, configModel);
        }
        return configModelMap.get(configGroup);
    }

    protected String getConfigValue(String configGroup, String key) {
        return getConfigValue(configGroup, key, null);
    }

    protected String getConfigValue(String configGroup, String key, String valueForNull) {
        String pkey = BaseUtils.getPropertyKey(getConfigName(), configGroup, key);
        return ConfigUtils.getConfigValue(pkey, valueForNull);
    }

    protected void checkNotNull(String value, String message) {
        if (StringUtils.isBlank(value)) {
            throw new ConfigModelRuntimeException(message);
        }
    }

    protected void checkNotNumber(String value, String message) {
        if (!StringUtils.isNumeric(value)) {
            throw new ConfigModelRuntimeException(message);
        }
    }

    /**
     * 获取配置标识名
     * 
     * @return
     */
    protected abstract String getConfigName();

    /**
     * 具体实现构造配置model，提供给具体集成provider
     * 
     * @param configGroup
     * @return
     */
    protected abstract IConfigModel makeConfigModel(String configGroup);

}
