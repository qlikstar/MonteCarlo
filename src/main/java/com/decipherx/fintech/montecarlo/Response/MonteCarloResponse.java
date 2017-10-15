package com.decipherx.fintech.montecarlo.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonteCarloResponse<T> extends BaseResponse {

    private Integer errorCode;
    private String errorMessage;

    private T result;

    public Integer getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MonteCarloResponse() {
    }

    public MonteCarloResponse(Integer errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public MonteCarloResponse(T successResponseObject) {
        super();
        this.setErrorMessage(null);
        this.setErrorCode(null);
        this.setStatus(OK_STATUS);
        this.result = successResponseObject;
    }

}
