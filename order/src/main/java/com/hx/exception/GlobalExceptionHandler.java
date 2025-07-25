package com.hx.exception;

import com.hx.entity.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常处理器
 *
 * @author Yuzhe Ma
 * @date 2018/11/12
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 Exception 异常
     *
     * @param httpServletRequest httpServletRequest
     * @param e                  异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        log.error("服务错误:", e);
        return new ResponseData("服务出错");
    }

    /**
     * 处理 BusinessException 异常
     *
     * @param httpServletRequest httpServletRequest
     * @param e                  异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseData businessExceptionHandler(HttpServletRequest httpServletRequest, BusinessException e) {
        log.info("业务异常。code:" + e.getCode() + "msg:" + e.getMsg());
        return new ResponseData(e.getCode(), e.getMsg());
    }
}
