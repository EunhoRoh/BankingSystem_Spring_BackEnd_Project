package com.eunho.bankingsystem_spring_v2.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@NoArgsConstructor
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String msg){
        super(msg);
    }

    public InvalidTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }




}
