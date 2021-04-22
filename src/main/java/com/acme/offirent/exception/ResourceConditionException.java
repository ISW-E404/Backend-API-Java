  
package com.acme.offirent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceConditionException extends  RuntimeException{
    public ResourceConditionException(String message) {
        super(message);
    }

    public ResourceConditionException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("Resource %s can not be changed with %s with value %s",resourceName,fieldName,fieldValue));
    }
}