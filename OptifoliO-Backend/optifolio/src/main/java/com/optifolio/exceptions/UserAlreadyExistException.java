package com.optifolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistException extends  ServiceException{
    public UserAlreadyExistException() {
        super(ErrorTag.USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
}
