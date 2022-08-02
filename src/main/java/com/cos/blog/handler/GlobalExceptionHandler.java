package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

    @ControllerAdvice //모든 Exception이 발생하면 이 Class로 들어오게 하는 Annotation
    @RestController
    public class GlobalExceptionHandler {
        //IllegalArgumentException에 대한 Exception이 발생하면 이곳으로 오라.
        //만일 모든 Exception을 하나로 처리하고 싶으면 Exception.class로 하면 된다.
        @ExceptionHandler(value = IllegalArgumentException.class)
        public String handleArgumentException(IllegalArgumentException e) {
            return "<h1>"+e.getMessage()+"</h1>";
        }
}
