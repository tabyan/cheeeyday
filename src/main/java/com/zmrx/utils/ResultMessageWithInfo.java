package com.zmrx.utils;

/**
 * Created by tabyan on 16-9-19.
 */
public class ResultMessageWithInfo {

    private boolean success;
    private String msg;

    private String chinese;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
