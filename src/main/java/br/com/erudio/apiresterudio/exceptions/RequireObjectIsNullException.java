package br.com.erudio.apiresterudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequireObjectIsNullException extends RuntimeException {

   public RequireObjectIsNullException(String msg){
    super(msg);
   }
    public RequireObjectIsNullException(){
        super("It's not allowed to persis a null object!");
    }
}
