package org.yyb.coral.core.redis;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.yyb.coral.Constants;
import org.yyb.coral.common.exception.CoralRuntimeException;
import org.yyb.coral.common.extension.SpiMeta;
import org.yyb.coral.core.configmodel.IConfigModel;
import org.yyb.coral.core.provider.AbstractIntegrateClientProvider;
import org.yyb.coral.core.provider.IntegrateOrderEnum;
import org.yyb.coral.core.redis.config.RedisConfigModel;

/**
 * redisson客户端集成实现
 * 
 * @author: yybg
 * @date: 2017年10月12日 下午5:05:43
 */
@SpiMeta(name = "default")
public class RedissonProviderDefaultImpl extends AbstractIntegrateClientProvider<RedissonClient> implements IRedissonProvider {

    public RedissonProviderDefaultImpl() {
        super(Constants.CORAL_REDIS, "Redissonn");
    }

    @Override
    protected Object doMakeClientPoolInternal(IConfigModel configModel) {
        if (configModel instanceof RedisConfigModel) {
            RedisConfigModel redisConfigModel = (RedisConfigModel) configModel;
            RedissonInitTypeEnum initTypeEnum = RedissonInitTypeEnum.getInitType(redisConfigModel.getRedissonInitType());
            RedissonClient redissonClient = null;
            switch (initTypeEnum) {
            case SINGLETON:
                redissonClient = getSingleton(redisConfigModel);
                break;
            case CLUSTER:
                redissonClient = getCluster(redisConfigModel);
                break;
            default:
                redissonClient = getSingleton(redisConfigModel);
                break;
            }
            return redissonClient;
        }
        return null;
    }

    @Override
    protected RedissonClient doGetClientInternal(Object clientPool) {
        return (RedissonClient) clientPool;
    }

    @Override
    public RedissonClient getRedissonClientDefault() {
        return getClientDefault();
    }

    @Override
    public RedissonClient getRedissonClient(String configGroup) {
        return getClient(configGroup);
    }

    private RedissonClient getSingleton(RedisConfigModel configModel) {
        Config config = new Config();
        // redisson 3.5.4,合法IPV4地址：//127.0.0.1:6379
        // 合法IPV6地址：redis://fe80::34d5:5959:fa49:3b05%22:6379、rediss://fe80::34d5:5959:fa49:3b05%22:6379
        if (StringUtils.isBlank(configModel.getHost())) {
            throw new CoralRuntimeException("Redisson client Singleton host can not null!");
        }
        String address = getAddress(configModel.getHost() + ":" + configModel.getPort());
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress(address).setPassword(configModel.getPassWord());
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    private RedissonClient getCluster(RedisConfigModel configModel) {
        Config config = new Config();
        // cluster state scan interval inmilliseconds
        String clusterNodes = configModel.getClusterNodes();
        if (StringUtils.isBlank(clusterNodes)) {
            throw new CoralRuntimeException("Redisson client clusterNodes can not null!");
        }
        // HostAndPort.fromString(hostPortString);
        // "127.0.0.1:7000", "127.0.0.1:7001"
        String[] nodes = clusterNodes.split(",");
        config.useClusterServers().setScanInterval(2000).addNodeAddress(nodes);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;

    }

    private RedissonClient getSentinel(RedisConfigModel configModel) {
        // Config config = new Config();
        // config.useSentinelServers().setMasterName("mymaster")
        // .addSentinelAddress("127.0.0.1:26389",
        // "127.0.0.1:26379").addSentinelAddress("127.0.0.1:26319");
        // RedissonClient redisson = Redisson.create(config);
        return null;
    }

    private RedissonClient getMasterSlave(RedisConfigModel configModel) {
        // Config config = new Config();
        // config.useMasterSlaveServers()
        // .setMasterAddress("127.0.0.1:6379")
        // .addSlaveAddress("127.0.0.1:6389", "127.0.0.1:6332",
        // "127.0.0.1:6419")
        // .addSlaveAddress("127.0.0.1:6399");
        // RedissonClient redisson = Redisson.create(config);
        return null;
    }

    private static String getAddress(String uri) {
        String[] parts = uri.split(":");
        if (parts.length - 1 >= 3) {
            // IPV6地址
            return uri;
        } else {
            if (uri.startsWith("//")) {
                return uri;
            }
            return "//" + uri;
        }
    }

    @Override
    public int getOrder() {
        return IntegrateOrderEnum.REDISSON.getOrder();
    }

    @Override
    protected void doDestroyClientPoolInternal(Object clientPool) {
        ((RedissonClient) clientPool).shutdown();
    }
}
