package com.cos.blog.handler;

import com.cos.blog.controller.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

    @ControllerAdvice //모든 Exception이 발생하면 이 Class로 들어오게 하는 Annotation
    @RestController
    public class GlobalExceptionHandler {
        //IllegalArgumentException에 대한 Exception이 발생하면 이곳으로 오라.
        //만일 모든 Exception을 하나로 처리하고 싶으면 Exception.class로 하면 된다.
        @ExceptionHandler(value = IllegalArgumentException.class)
        public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
            return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        }
}
