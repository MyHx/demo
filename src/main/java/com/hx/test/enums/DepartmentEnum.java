package com.hx.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentEnum {
    FINANCING(100, "融资"),
    TRANSACTION(200, "交易"),
    SETTLEMENT(300, "清结算"),
    ;

    private final Integer code;
    private final String desc;

}
