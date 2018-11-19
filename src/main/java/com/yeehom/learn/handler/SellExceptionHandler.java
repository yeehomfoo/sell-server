package com.yeehom.learn.handler;

import com.yeehom.learn.Util.ResultVOUtil;
import com.yeehom.learn.VO.ResultVO;
import com.yeehom.learn.exception.BankResponseException;
import com.yeehom.learn.exception.SellException;
import com.yeehom.learn.exception.SellerAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeExcepiton() {
        // TODO redirect to Wechat QR-Code page
        return new ModelAndView("redirect:"
            .concat("https://blog.yeehomfoo.com"));
    }

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException sellException) {
        return ResultVOUtil.error(sellException.getCode(), sellException.getMessage());
    }

    @ExceptionHandler(BankResponseException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleBankResponseException() {

    }
}
