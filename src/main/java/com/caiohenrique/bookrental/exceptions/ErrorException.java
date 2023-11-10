package com.caiohenrique.bookrental.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorException {
    private final int status;
    private final String message;

}
