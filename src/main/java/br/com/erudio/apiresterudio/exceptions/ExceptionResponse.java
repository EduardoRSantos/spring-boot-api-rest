package br.com.erudio.apiresterudio.exceptions;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExceptionResponse implements Serializable {
    
    private Date timestamp;
    private String message;
    private String details;
    
}
