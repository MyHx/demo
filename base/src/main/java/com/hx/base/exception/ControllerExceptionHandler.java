package com.hx.base.exception;


import com.hx.base.constant.exception.ErrorCodeEnum;
import com.hx.base.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.hx.base.constant.exception.ErrorCodeConstant.*;


/**
 * @author Administrator
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    private static final Integer maxSize = 60;

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public final ResponseEntity handleGeneralException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        Map<String, Object> map = new HashMap<>(4);
        if (ex instanceof SessionTimeoutException) {
            log.error("Session Timeout Exception!");
            map.put("ret", SESSION_TIME_OUT_CODE);
            map.put("retmsg", ex.getMessage());
        } else if (ex instanceof NoAuthenticationException) {
            log.error("No Authentication Exception!");
            map.put("ret", NO_AUTHENTICATION_CODE);
            map.put("retmsg", "身份认证失败");
        } else if (ex instanceof IdentityVerificationTimeout) {
            log.error("No Authentication Exception!");
            map.put("ret", IDENTITY_VERIFICATION_TIMEOUT_CODE);
            map.put("retmsg", ex.getMessage());
        } else if (ex instanceof SQLException) {
            log.error("SQLException!", ex);
            map.put("ret", SYSTEM_EXCEPTION_MSG);
            map.put("retmsg", "网络异常");
        } else {
            log.error("ERROR", ex);
            map.put("ret", SYSTEM_EXCEPTION_CODE);
            map.put("retmsg", SYSTEM_EXCEPTION_MSG);

        }
        return new ResponseEntity(JsonUtil.encode(map), headers, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        log.error("WARN", ex);
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            FieldError fieldError = e.getBindingResult().getFieldError();
            return getValidResponseEntity(headers, fieldError);
        } else if (ex instanceof BindException) {
            BindException e = (BindException) ex;
            FieldError fieldError = e.getBindingResult().getFieldError();
            return getValidResponseEntity(headers, fieldError);
        }

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }
        Map<String, Object> map = new HashMap<>(4);
        map.put("ret", SYSTEM_EXCEPTION_CODE);
        map.put("retmsg", SYSTEM_EXCEPTION_MSG);
        return new ResponseEntity(JsonUtil.encode(map), headers, status);
    }

    private ResponseEntity<Object> getValidResponseEntity(HttpHeaders headers, FieldError fieldError) {
        log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(), fieldError.getField());
        Map<String, Object> map = new HashMap<>(4);
        map.put("ret", ErrorCodeEnum.PARAM_ERROR.getCode());
        map.put("retmsg", fieldError.getDefaultMessage());
        return new ResponseEntity(map, headers, HttpStatus.OK);
    }

}
