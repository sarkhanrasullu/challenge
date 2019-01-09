package com.outfittery.dto;

public final class ResponseDto {

    private String msg;
    private int errCode;
    private Object obj;

    private ResponseDto(String msg, int errCode, Object obj) {
        this.msg = msg;
        this.errCode = errCode;
        this.obj = obj;
    }

    private ResponseDto(Object obj) {
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

    public static ResponseDto instance(Object obj) {
        return new ResponseDto(obj);
    }

    public static ResponseDto instanceSuccess(Object obj) {
        return new ResponseDto("SUCCESS", 0, obj);
    }

}
