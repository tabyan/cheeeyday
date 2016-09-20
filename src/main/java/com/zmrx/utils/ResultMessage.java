package com.zmrx.utils;

import javax.persistence.Entity;

/**
 * Created by tabyan on 16-8-21.
 */
public class ResultMessage {

    private boolean success;
    private String msg;

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
}
