package com.optifolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PortfolioRecordNotFoundException extends  ServiceException {
    public PortfolioRecordNotFoundException() {
        super(ErrorTag.POSITION_RECORD_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
