package org.yyb.coral.core.redis.config;

import org.apache.commons.lang3.StringUtils;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.common.utils.NumberUtils;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.configmodel.provider.AbstractConfigModelProvider;

/**
 * jedis配置提供
 * 
 * @author: yybg
 * @date: 2017年7月19日 下午4:00:18
 */
@SpiMeta(name = "redis")
public class RedisConfigModelProviderImpl extends AbstractConfigModelProvider {

    public static final String CLUSTER_NODES_KEY = "clusternodes";

    public static final String HOST_KEY = "host";

    public static final String PORT_KEY = "port";

    public static final String USERNAME_KEY = "username";

    public static final String MIMAPAD_KEY = "mimapad";

    public static final String PASSWORD_KEY = "password";

    public static final String TIMEOUT_KEY = "timeout";

    public static final int PORT_DEFAULT = 6379;

    public static final int TIMEOUT_DEFAULT = 2000;

    public RedisConfigModelProviderImpl() {
        super();
    }

    @Override
    protected String getConfigName() {
        return "redis";
    }

    @Override
    protected IConfigModel makeConfigModel(String configGroup) {
        RedisConfigModel configModel = new RedisConfigModel();
        // 配置ID为配置名-分组
        configModel.setId(getConfigName() + "-" + configGroup);

        // 集群信息
        String clusterNodes = getConfigValue(configGroup, CLUSTER_NODES_KEY);
        // redis的集群配置信息字符串，ip:poort,ip:port
        configModel.setClusterNodes(clusterNodes);

        String hostStr = getConfigValue(configGroup, HOST_KEY);
        configModel.setHost(hostStr);

        String portStr = getConfigValue(configGroup, PORT_KEY);
        configModel.setPort(NumberUtils.toInt(portStr, PORT_DEFAULT));

        String userNameStr = getConfigValue(configGroup, USERNAME_KEY);
        configModel.setUserName(userNameStr);

        String pwdStr = getConfigValue(configGroup, PASSWORD_KEY);
        if (StringUtils.isBlank(pwdStr)) {
            // 有些安全扫描password、pwd等关键字，可以配置为mimapad
            pwdStr = getConfigValue(configGroup, MIMAPAD_KEY);
        }
        // TODO 后面可以统一解密，配置的密码可以为AES加密密文
        configModel.setPassWord(pwdStr);

        String timeOutStr = getConfigValue(configGroup, TIMEOUT_KEY);
        configModel.setTimeOut(NumberUtils.toInt(timeOutStr, TIMEOUT_DEFAULT));

        // 优化参数
        String maxTotalStr = getConfigValue(configGroup, "maxTotal");
        configModel.setMaxTotal(NumberUtils.toInt(maxTotalStr, 10));

        String maxIdleStr = getConfigValue(configGroup, "maxIdle");
        configModel.setMaxIdle(NumberUtils.toInt(maxIdleStr, 10));

        String minIdleStr = getConfigValue(configGroup, "minIdle");
        configModel.setMinIdle(NumberUtils.toInt(minIdleStr, 0));

        String maxWaitMillisStr = getConfigValue(configGroup, "maxWaitMillis");
        configModel.setMaxWaitMillis(NumberUtils.toInt(maxWaitMillisStr, 500));

        return configModel;
    }
}
