package com.pdt_sales.pdt_sales;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.exception.SalesNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  // RESOURCE NOT FOUND EXCEPTION HANDLER
  @ExceptionHandler({ SalesNotFoundException.class, ProductNotFoundException.class })
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // DELETION EXCEPTION HANDLER
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Product does not exist.", LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // VALIDATION EXCEPTION HANDLER
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

    // Get the list of validation errors
    List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

    // Create a Stringbuild to store all error messages
    StringBuilder sb = new StringBuilder();

    // Loop and append the errors
    for (ObjectError error : validationErrors) {
      sb.append(error.getDefaultMessage() + ". ");
    }

    ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

  }

  // GENERAL EXCEPTION HANDLER
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    // Add logging here
    // logger.error(ex.getMessage());
    // Return a generic error response so as not to leak sensitive information
    ErrorResponse errorResponse = new ErrorResponse("Something went wrong", LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
