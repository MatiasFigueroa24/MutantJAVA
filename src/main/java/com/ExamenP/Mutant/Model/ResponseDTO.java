package com.ExamenP.Mutant.Model;

public class ResponseDTO {
    private int ErrorCode;
    private boolean Ok;
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public boolean isOk() {
        return getErrorCode()==0;
    }

}
