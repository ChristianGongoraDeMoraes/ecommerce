package ecommerceBESB.ecommerce.Errors.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ecommerceBESB.ecommerce.Errors.Exceptions.UserNotFoundException;
import ecommerceBESB.ecommerce.Errors.Exceptions.RoleNotValidException;
import ecommerceBESB.ecommerce.Errors.Exceptions.ProductNotFoundException;
import ecommerceBESB.ecommerce.Errors.Exceptions.OrderNotFoundException;


import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<String> OrderNotFoundException(OrderNotFoundException exp){
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(exp.getMessage());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<String> ProductNotFoundException(ProductNotFoundException exp){
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(exp.getMessage());
  }

  @ExceptionHandler(RoleNotValidException.class)
  public ResponseEntity<String> RoleNotValidException(RoleNotValidException exp){
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(exp.getMessage());
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> handle(UserNotFoundException exp) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(exp.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
    var errors = new HashMap<String, String>();

    exp.getBindingResult().getAllErrors()
            .forEach(error -> {
              var fieldName = ((FieldError) error).getField();
              var errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    return ResponseEntity
            .status(BAD_REQUEST)
            .body(new ErrorResponse(errors));
  }
}
