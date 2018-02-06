package org.yyb.coral.core.model;

import java.io.Serializable;

/**
 * 通用结果接口
 * 
 * @author: yybg
 * @date: 2017年10月30日 上午8:57:36
 */
public interface IResultModel<T> extends Serializable {

    public void setReturnCode(String returnCode);

    public void setReturnMessage(String returnMessage);

    public String getReturnCode();

    public String getReturnMessage();

    public String getRequestId();

    public boolean isSuccess();

    public T getReturnValue();
}
