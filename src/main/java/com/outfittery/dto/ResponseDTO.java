package com.outfittery.dto;

public class ResponseDTO {

    private String msg;
    private int errCode;
    private Object obj;

    public ResponseDTO(String msg, int errCode, Object obj) {
        this.msg = msg;
        this.errCode = errCode;
        this.obj = obj;
    }

    public ResponseDTO(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    
    
}
