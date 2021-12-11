package com.bookingmanager.springbootmicroservice.common;

public enum ErrorMessage {
    DATE_ERROR(1001, "date error"),
    NAME_ERROR(1002, "name error"),
    NUMBER_ERROR(1003, "roomNumber error"),
    UNKNOWN_ERROR(9999, "unknown error")
    ;

    private int code;
    private String msg;

    ErrorMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int code(){
        return code;
    }

    public String msg(){
        return msg;
    }

    public static ErrorMessage getErrorCode(int code){
        for (ErrorMessage it: ErrorMessage.values()){
            if(it.code == code){
                return it;
            }
        }
        return UNKNOWN_ERROR;
    }
}
