package com.quant.ecommerce.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EcommerceBusinessException extends RuntimeException {

    public EcommerceBusinessException() {super();}

    public EcommerceBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public EcommerceBusinessException(String message) {
        super(message);
    }

    public EcommerceBusinessException(Throwable cause) {
        super(cause);
    }

}
