package com.optifolio.exceptions;

import org.springframework.http.HttpStatus;



public class CapitalRecordNotFoundException extends  ServiceException{
    public CapitalRecordNotFoundException() {
        super(ErrorTag.CAPITAL_RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
