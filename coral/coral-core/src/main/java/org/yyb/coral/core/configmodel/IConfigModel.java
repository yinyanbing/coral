package org.yyb.coral.core.configmodel;

/**
 * 配置model顶层接口
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:52:14
 */
public interface IConfigModel {
    /**
     * 获取配置标识ID
     * 
     * @return
     */
    public String getId();

    /**
     * 设置配置标识ID
     * 
     * @param id
     */
    public void setId(String id);

}
