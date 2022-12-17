package com.quant.ecommerce.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class BusinessHandlerExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EcommerceBusinessException.class})
    ResponseEntity handleException(EcommerceBusinessException ex, WebRequest webRequest) {
        log.debug(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),
            ((ServletWebRequest)webRequest).getRequest().getRequestURI());
        return ResponseEntity.badRequest().body(apiError);
    }

}
