package org.yyb.coral.core.syncinvoke;

import org.yyb.coral.common.exception.CoralRuntimeException;

/**
 * 同步调用异常
 * 
 * @author: yybg
 * @date: 2017年10月26日 下午4:55:33
 *
 */
public class SyncInvokeRuntimeException extends CoralRuntimeException {

	private static final long serialVersionUID = 1592174557251842264L;

	private static final String SEPARATOR = " : ";

	/**
	 * 错误代码
	 */
	private String returnCode;

	/**
	 * 详细错误信息
	 */
	private String returnMessage;

	private String caseMessage;

	public SyncInvokeRuntimeException() {
		super();
	};

	public SyncInvokeRuntimeException(Throwable cause) {
		super(cause);
	}

	public SyncInvokeRuntimeException(String errorMessage) {
		super(errorMessage);
	};

	public SyncInvokeRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	};

	/**
	 * 设置异常信息
	 * 
	 * @param message
	 */
	public SyncInvokeRuntimeException(String errorCode, String errorMessage) {
		super(errorCode + SEPARATOR + errorMessage);
		this.returnCode = errorCode;
		this.returnMessage = errorMessage;
	}

	/**
	 * 设置异常信息和原始堆栈
	 * 
	 * @param message
	 */
	public SyncInvokeRuntimeException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode + SEPARATOR + errorMessage, cause);
		this.returnCode = errorCode;
		this.returnMessage = errorMessage;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getReturnMessage() {

		return returnMessage;
	}

	public String getReturnErrorStackTrace() {
		Throwable cause = getCause();
		if (cause != null) {
			StringBuffer buf = new StringBuffer();
			if (returnMessage != null) {
				buf.append(returnMessage).append("; ");
			}
			buf.append("nested exception is ").append(cause);
			return buf.toString();
		} else {
			return returnMessage;
		}

	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setReturnCode(String errorCode) {
		this.returnCode = errorCode;
	}

	public SyncInvokeRuntimeException addReturnCode(String errorCode) {
		this.returnCode = errorCode;
		return this;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setReturnMessage(String errorMessage) {
		this.returnMessage = errorMessage;
	}

	public SyncInvokeRuntimeException addReturnMessage(String errorMessage) {
		this.returnMessage = errorMessage;
		return this;
	}

	public SyncInvokeRuntimeException addCause(String caseMessage) {
		this.caseMessage = caseMessage;
		return this;
	}

	public String getCaseMessage() {
		return caseMessage;
	}

	public void setCaseMessage(String caseMessage) {
		this.caseMessage = caseMessage;
	}
}
