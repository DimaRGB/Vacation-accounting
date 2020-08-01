package com.github.dmytr0.vacation.exception;

import java.text.MessageFormat;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
