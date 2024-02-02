package br.com.erudio.apiresterudio.exceptions.handler;

import java.util.Date;

import br.com.erudio.apiresterudio.exceptions.RequireObjectIsNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.erudio.apiresterudio.exceptions.ExceptionResponse;
import br.com.erudio.apiresterudio.exceptions.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponserEntityExceptionHandler extends ResponseEntityExceptionHandler {
    

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception e, WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(RequireObjectIsNullException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(RequireObjectIsNullException e, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }


}
