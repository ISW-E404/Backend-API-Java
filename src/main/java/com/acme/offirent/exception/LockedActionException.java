  
package com.acme.offirent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class LockedActionException extends  RuntimeException{
    public LockedActionException(String message) {
        super(message);
    }
}