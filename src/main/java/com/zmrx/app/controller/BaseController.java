package com.zmrx.app.controller;

import com.zmrx.utils.ResultMessage;
import com.zmrx.utils.ResultMessageWithInfo;

/**
 * Created by tabyan on 16-8-21.
 */
public class BaseController {

    public ResultMessage Msg(boolean success,String msg){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(success);
        resultMessage.setMsg(msg);
        return resultMessage;
    }

    public ResultMessageWithInfo MsgI(boolean success,String msg,String info){
        ResultMessageWithInfo resultMessageWithInfo = new ResultMessageWithInfo();
        resultMessageWithInfo.setSuccess(success);
        resultMessageWithInfo.setMsg(msg);
        resultMessageWithInfo.setChinese(info);
        return resultMessageWithInfo;
    }
}
