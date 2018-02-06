package org.yyb.coral.chain.exception;

import org.yyb.coral.common.exception.CoralRuntimeException;

public class CoralChainRuntimeException extends CoralRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1341538507297710043L;

	public CoralChainRuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CoralChainRuntimeException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
		// TODO Auto-generated constructor stub
	}

	public CoralChainRuntimeException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
		// TODO Auto-generated constructor stub
	}

	public CoralChainRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		// TODO Auto-generated constructor stub
	}

	public CoralChainRuntimeException(String errorMessage) {
		super(errorMessage);
		// TODO Auto-generated constructor stub
	}

	public CoralChainRuntimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
