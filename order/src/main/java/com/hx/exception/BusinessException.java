package com.hx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hexian
 * @date 2021/5/26 17:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int code;
    private String msg;
}
