package com.optifolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PositionRecordAlreadyExistException extends  ServiceException{
    public PositionRecordAlreadyExistException() {
        super(ErrorTag.POSITION_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
