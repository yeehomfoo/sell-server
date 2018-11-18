package com.yeehom.learn.handler;

import com.yeehom.learn.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeExcepiton() {
        // TODO redirect to Wechat QR-Code page
        return new ModelAndView("redirect:"
            .concat("http://www.baidu.com"));
    }
}
