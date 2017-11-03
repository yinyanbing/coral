package org.yyb.coral.core.provider;

/**
 * <pre>
 * 集成客户端提供者接口，主要用于三方集成
 * 从coralconfig.properties读取配置信息并生成需要的集成客户端； 支持统一客户端分组获取
 * </pre>
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午10:03:26
 */
public interface IIntegrateClientProvider<T> {
    /**
     * 获取默认的集成客户端
     * 
     * @return
     */
    public T getClientDefault();

    /**
     * 获取某配置组的集成客户端
     * 
     * @param configGroup
     * @return
     */
    public T getClient(String configGroup);

}
