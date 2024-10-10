package com.xlsxreader.controllers;

import com.xlsxreader.exceptions.EmptyXlsxFileException;
import com.xlsxreader.exceptions.WrongIndexNumberException;
import com.xlsxreader.exceptions.XlsxFileNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionsHandler {

    @ExceptionHandler(XlsxFileNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleXlsxFileNotFoundException(XlsxFileNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(WrongIndexNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleWrongIndexNumberException(WrongIndexNumberException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(EmptyXlsxFileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleEmptyXlsxFileException(EmptyXlsxFileException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @AllArgsConstructor
    @Getter
    private static class ErrorResponse {
        @Schema(description = "Сообщение об ошибке.")
        private final String message;
    }

}
