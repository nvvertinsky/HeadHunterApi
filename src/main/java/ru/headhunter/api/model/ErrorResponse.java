package ru.headhunter.api.model;


public class ErrorResponse extends RuntimeException {
    private String msg;

    public ErrorResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
