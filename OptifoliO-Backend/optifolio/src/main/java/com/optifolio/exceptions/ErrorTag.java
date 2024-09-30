package com.optifolio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTag {
    USER_NOT_FOUND("user_not_found"),
    BAD_CREDENTIALS("bad_credentials"),
    USER_ALREADY_EXISTS("user_already_exist");


    private final String tag;
    private final String description;

    ErrorTag(String tag) {
        this.tag = tag;
        this.description = null;
    }
}

