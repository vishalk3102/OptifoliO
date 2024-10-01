package com.optifolio.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PortfolioRecordAlreadyExistException extends ServiceException{
    public PortfolioRecordAlreadyExistException() {
        super(ErrorTag.PORTFOLIO_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
