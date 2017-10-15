package com.decipherx.fintech.montecarlo.Response;

/**
 * Created by sanketmishra on 10/14/17.
 */

public abstract class BaseResponse {
    protected final static int ERROR_STATUS = -1;
    protected final static int OK_STATUS = 0;
    private int status = ERROR_STATUS;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
