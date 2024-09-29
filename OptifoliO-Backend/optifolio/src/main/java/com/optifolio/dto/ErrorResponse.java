package com.optifolio.dto;

import com.optifolio.exceptions.ErrorTag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private  final ErrorTag errorTag;
    private  final String message;
}
