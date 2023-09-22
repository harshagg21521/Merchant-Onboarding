package com.java_project.marchant_onboarding.ExceptionHandlers;

import com.java_project.marchant_onboarding.ExceptionHandlers.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> HandleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return errMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String,String> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String ,String> errMap=new HashMap<>();
        errMap.put("ErrorMessage", ex.getMessage());
        return errMap;
    }

}
