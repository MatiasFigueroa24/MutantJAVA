package com.ExamenP.Mutant.Model;

public enum EnumErrorCode {

    InvalidDataReceived("InvalidDataReceived",1),
    IncorrectNitrogenBase("IncorrectNitrogenBase",2),
    GeneralException("GeneralError",3);
    private String textErrorCode;
    private int errorCode;

    private EnumErrorCode (String _textErrorCode,int _errorCode){
        this.errorCode = _errorCode;
        this.textErrorCode = _textErrorCode;
    }

    public String getTextErrorCode() {
        return textErrorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
