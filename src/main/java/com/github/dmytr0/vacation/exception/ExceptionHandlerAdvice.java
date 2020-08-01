package com.github.dmytr0.vacation.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Log4j2
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    @ResponseBody
    public String handleRequestUnauthorizedErrors(Exception e) {
        log.debug("Encountered exception while processing request, responding with UNAUTHORIZED", e);
        return "Encountered exception while processing request: " + e.getMessage();
    }
}
