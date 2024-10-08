package com.optifolio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTag {
    USER_NOT_FOUND("user_not_found"),
    CAPITAL_RECORD_NOT_FOUND("capital_record_not_found"),
    PORTFOLIO_RECORD_NOT_FOUND("portfolio_record_not_found"),
    POSITION_RECORD_NOT_FOUND("position_record_not_found"),
    BAD_CREDENTIALS("bad_credentials"),
    USER_ALREADY_EXISTS("user_already_exist"),
    PORTFOLIO_ALREADY_EXISTS("portfolio_already_exist"),
    POSITION_ALREADY_EXISTS("position_already_exist");


    private final String tag;
    private final String description;

    ErrorTag(String tag) {
        this.tag = tag;
        this.description = null;
    }
}

