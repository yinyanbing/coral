package org.yyb.coral.core;

/**
 * 启动或销毁信息返回，用于启动或停止是记录日志，便于查看
 * 
 * @author: yybg
 * @date: 2017年9月26日 下午4:39:53
 */
public interface IStartStopInfo extends ICoralName {
    /**
     * 获取启动相关详细信息
     * 
     * @return
     */
    public String getStartInfo();

    /**
     * 获取停止相关详细信息
     * 
     * @return
     */
    public String getStopInfo();
}
