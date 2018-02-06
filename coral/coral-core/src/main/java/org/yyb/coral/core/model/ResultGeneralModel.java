package org.yyb.coral.core.model;

import org.apache.commons.lang3.StringUtils;

/**
 * 默认的结果MODEL
 * 
 * @author: yybg
 * @date: 2017年10月30日 上午9:02:24
 */
public class ResultGeneralModel<T> implements IResultModel<T> {

    private static final long serialVersionUID = 5629494973776415516L;

    private String requestId;

    /**
     * 0:默认表示成功
     */
    private String returnCode = "0";

    private String returnMessage;

    private IErrors errors;

    private T returnValue;

    public ResultGeneralModel(IErrors errors) {
        super();
        this.errors = errors;
        this.setReturnCode(errors.getCode());
        this.setReturnMessage(errors.getMessage());
    }

    public ResultGeneralModel(T returnValue) {
        this.returnValue = returnValue;
    }

    public ResultGeneralModel(T returnValue, String returnCode, String returnMessage) {
        this.returnValue = returnValue;
        this.setReturnCode(returnCode);
        this.setReturnMessage(returnMessage);
    }

    @Override
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;

    }

    @Override
    public String getReturnCode() {
        return returnCode;
    }

    @Override
    public String getReturnMessage() {
        return returnMessage;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public boolean isSuccess() {
        return StringUtils.isBlank(returnCode) ? false : StringUtils.equals("0", returnCode);
    }

    @Override
    public T getReturnValue() {
        return returnValue;
    }

}
