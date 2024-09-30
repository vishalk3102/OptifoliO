package com.optifolio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserNotFoundException extends ServiceException{
    public UserNotFoundException() {
        super(ErrorTag.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
