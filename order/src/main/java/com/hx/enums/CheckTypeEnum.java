package com.hx.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对账模式类型
 */
public enum CheckTypeEnum implements EnumType {
    INNER_PAY(10, "内部放款对账"),
    INNER_REPAY(20, "内部还款对账"),
    INNER_EXPECT(30, "内部还款计划对账"),
    EXTERNAL_EXPECT(40, "外部还款计划对账"),
    EXTERNAL_REPAY(50, "外部还款对账"),
    EXTERNAL_ACCOUNT_REPAY(60, "外部扣款回盘对账"),
    ;

    /**
     * code
     */
    private int code;
    /**
     * desc
     */
    private String desc;

    /**
     * 数据类型
     */
    private int dataType;

    private static Map<Integer, EnumType> map = initMap();

    private static Map<Integer, EnumType> initMap() {
        Map<Integer, EnumType> map = new LinkedHashMap<>();
        for (CheckTypeEnum one : CheckTypeEnum.values()) {
            map.put(one.getCode(), one);
        }
        return map;
    }

    public static CheckTypeEnum getInstance(int code) {
        return (CheckTypeEnum) map.get(code);
    }

    private CheckTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
        this.dataType = code;
    }

    private CheckTypeEnum(int code, String desc, int dataType) {
        this.code = code;
        this.desc = desc;
        this.dataType = dataType;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public int getDataType() {
        return dataType;
    }

}
