package org.yyb.coral.core.model;

/**
 * 错误接口
 * 
 * @ClassName: Errors
 * @author: yybg
 * @date: 2016年10月9日 上午10:26:03
 */
public interface IErrors {
    /**
     * 获取异常编码
     * 
     * @return
     */
    public String getCode();

    public String getMessage();

    public String getSolution();
}
