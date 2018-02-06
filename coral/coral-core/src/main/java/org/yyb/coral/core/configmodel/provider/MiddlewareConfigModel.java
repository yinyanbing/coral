package org.yyb.coral.core.configmodel.provider;

import org.yyb.coral.core.configmodel.AbstractConfigModel;

/**
 * 中间件配置
 * 
 * @author: yybg
 * @date: 2017年7月19日 下午2:11:30
 */
public class MiddlewareConfigModel extends AbstractConfigModel {

    /**
     * 
     */
    private static final long serialVersionUID = -4971403255834696835L;

    // 中间件基础信息
    /**
     * 地址IP
     */
    protected String host;

    /**
     * 端口
     */
    protected int port;

    /**
     * 用户名
     */
    protected String userName;

    /**
     * 密码
     */
    protected String passWord;

    /**
     * 获取资源超时时间
     */
    protected int timeOut;

    // 一般中间件客户端是池化的

    /**
     * // 最大资源数
     */
    protected int maxTotal;

    /**
     * 最大空闲资源数
     */
    protected int maxIdle;

    /**
     * 最小空闲资源数
     */
    protected int minIdle;

    /**
     * 最大等待时间，毫秒
     */
    protected int maxWaitMillis;

    /**
     * 获取资源时是否测试
     */
    protected boolean testOnBorrow;

    /**
     * <pre>
     * 集群的配置，需要具体客户端实例化时判断分析
     * 集群节点配置字符串，一般为ip:port,ip:port
     * </pre>
     */
    protected String clusterNodes;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

}
