package com.mitrais.vehicle_service_system.advice;

import com.mitrais.vehicle_service_system.dto.ErrorMessageResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({EntityNotFoundException.class, EntityExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleEntityExistAndNotFoundException(PersistenceException exception) {
        return new ErrorMessageResponse(exception.getMessage());
    }
}
