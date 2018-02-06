package org.yyb.coral.core.configmodel;

import org.yyb.coral.common.exception.CoralRuntimeException;

/**
 * 配置相关异常
 * 
 * @author: yybg
 * @date: 2017年10月24日 上午9:49:18
 */
public class ConfigModelRuntimeException extends CoralRuntimeException {

    private static final long serialVersionUID = -8262546173213682557L;

    public ConfigModelRuntimeException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ConfigModelRuntimeException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
        // TODO Auto-generated constructor stub
    }

    public ConfigModelRuntimeException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        // TODO Auto-generated constructor stub
    }

    public ConfigModelRuntimeException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        // TODO Auto-generated constructor stub
    }

    public ConfigModelRuntimeException(String errorMessage) {
        super(errorMessage);
        // TODO Auto-generated constructor stub
    }

    public ConfigModelRuntimeException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
