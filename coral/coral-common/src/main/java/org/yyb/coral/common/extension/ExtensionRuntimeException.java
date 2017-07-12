package org.yyb.coral.common.extension;

import org.yyb.coral.common.exception.CoralRuntimeException;

/**
 * 可SPI扩展异常
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:34:42
 */
public class ExtensionRuntimeException extends CoralRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262546173213682557L;

	public ExtensionRuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtensionRuntimeException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
		// TODO Auto-generated constructor stub
	}

	public ExtensionRuntimeException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public ExtensionRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		// TODO Auto-generated constructor stub
	}

	public ExtensionRuntimeException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public ExtensionRuntimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
