package com.optifolio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTag {
    FRAMEWORK_NOT_FOUND("framework_not_found"),
    CONTROL_NOT_FOUND("control_not_found"),
    CHECKLIST_NOT_FOUND("checklist_not_found"),
    USER_NOT_FOUND("user_not_found"),
    ROLE_NOT_FOUND("role_not_found"),
    FRAMEWORK_CATEGORY_NOT_FOUND("framework_category_not_found"),
    FRAMEWORK_CATEGORY_ALREADY_EXISTS("framework_category_already_exists"),
    CONTROL_CATEGORY_NOT_FOUND("control_category_not_found"),
    DEPARTMENT_NOT_FOUND("department_not_found"),
    PERMISSION_NOT_FOUND("permission_not_found"),
    CONTROL_CATEGORY_ALREADY_EXISTS("control_category_already_exists"),
    CONTROL__ALREADY_EXISTS("control_already_exists"),
    CHECKLIST_ALREADY_EXISTS("checklist_already_exists"),
    ROLE_ALREADY_EXISTS("role_already_exists"),
    SECURITY_COMPLIANCE_NOT_FOUND("security_compliance_not_found"),
    BAD_CREDENTIALS("bad_credentials"),
    USER_EMAIL_ALREADY_EXISTS("user_email_already_exist"),
    USER_EMPCODE_ALREADY_EXISTS("user_empcode_already_exists"),
    CHECKLIST_UPDATE_DENIED("checklist_update_denied"),
    PERIODICITY_UPDATE_DENIED("periodicity_update_denied");
    private final String tag;
    private final String description;

    ErrorTag(String tag) {
        this.tag = tag;
        this.description = null;
    }
}

