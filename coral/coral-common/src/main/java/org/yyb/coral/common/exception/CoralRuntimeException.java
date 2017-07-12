package org.yyb.coral.common.exception;

/**
 * service异常的基类，包含<code>returnCode</code>和<code>returnMessage</code>
 * service层如果需要实现自己的业务逻辑以及参数验证，请继承这个异常，并将异常描述信息作为message返回。
 * 
 * @author: yybg
 * @date: 2017年7月12日 下午4:31:28
 */
public class CoralRuntimeException extends RuntimeException {

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

	public CoralRuntimeException() {
		super();
	};

	public CoralRuntimeException(Throwable cause) {
		super(cause);
	}

	public CoralRuntimeException(String errorMessage) {
		super(errorMessage);
	};

	public CoralRuntimeException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	};

	/**
	 * 设置异常信息
	 * 
	 * @param message
	 */
	public CoralRuntimeException(String errorCode, String errorMessage) {
		super(errorCode + SEPARATOR + errorMessage);
		this.returnCode = errorCode;
		this.returnMessage = errorMessage;
	}

	/**
	 * 设置异常信息和原始堆栈
	 * 
	 * @param message
	 */
	public CoralRuntimeException(String errorCode, String errorMessage, Throwable cause) {
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

	public CoralRuntimeException addReturnCode(String errorCode) {
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

	public CoralRuntimeException addReturnMessage(String errorMessage) {
		this.returnMessage = errorMessage;
		return this;
	}

	public CoralRuntimeException addCause(String caseMessage) {
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
