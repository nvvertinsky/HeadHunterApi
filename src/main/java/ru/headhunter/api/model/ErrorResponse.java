package ru.headhunter.api.model;


public class ErrorResponse {
    private String errMsg;

    public ErrorResponse(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getMsg() {
        return errMsg;
    }

    public void setMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
