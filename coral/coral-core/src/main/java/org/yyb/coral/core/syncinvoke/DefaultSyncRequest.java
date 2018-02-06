package org.yyb.coral.core.syncinvoke;

import java.util.Map;

import org.yyb.coral.common.utils.BaseUtils;

public class DefaultSyncRequest implements ISyncRequest {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 请求数据，通过普通map包装
     */
    private Map<String, Object> requestData;

    public DefaultSyncRequest(Map<String, Object> requestData) {
        this.requestId = BaseUtils.getUUIDText();
        this.requestData = requestData;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public Map<String, Object> getRequestData() {
        return this.requestData;
    }

    @Override
    public void setRequestData(Map<String, Object> requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "DefaultSyncRequest [requestId=" + requestId + ", requestData=" + requestData + "]";
    }

}
