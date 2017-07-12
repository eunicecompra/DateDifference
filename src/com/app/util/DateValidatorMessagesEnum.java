package com.app.util;


public enum DateValidatorMessagesEnum {
    INVALID_DATE_FORMAT("Please input the dates in DD MM YYYY format i.e. 30 07 2010"),
    INVALID_ARGUMENTS("Please input 2 dates i.e. DD MM YYYY, DD MM YYYY"),
    INVALID_YEAR("Oops sorry, you can only input dates between 1900 and 2010.");

    private String displayMsg;

    DateValidatorMessagesEnum(String displayMsg){
        this.displayMsg = displayMsg;
    }

    public String getDisplayMsg(){
        return displayMsg;
    }


}
