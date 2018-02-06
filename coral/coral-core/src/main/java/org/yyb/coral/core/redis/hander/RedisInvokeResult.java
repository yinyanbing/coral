package org.yyb.coral.core.redis.hander;

import java.io.Serializable;

/**
 * redis调用结果
 * 
 * @author: yybg
 * @date: 2017年10月10日 下午3:45:22
 */
public class RedisInvokeResult<T> implements Serializable {

    private static final long serialVersionUID = -4967701006675396887L;

    /**
     * 返回值
     */
    private T value = null;

    /**
     * 调用返回值,0:调用成功;1:调用异常；2：无redis客户端
     */
    private RedisInvokeResultEnum resultCode = RedisInvokeResultEnum.SUCCESS;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public RedisInvokeResultEnum getResultCode() {
        return resultCode;
    }

    public void setResultCode(RedisInvokeResultEnum resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSuccess() {
        return RedisInvokeResultEnum.SUCCESS == resultCode;
    }
}
