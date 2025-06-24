package com.hx.base.constant;

import lombok.Getter;

/**
 * 顾问类型枚举
 */
@Getter
public enum ConsultantEnum {
    STORE_MANAGER(1, "店长"),
    MANAGER_PHARMACIST(2, "店经理兼药师"),
    HEALTH_CONSULTANT(3, "健康顾问"),
    PROFESSIONAL_PHARMACIST(4, "职业药师"),
    UNKNOWN(-1, "未知类型");  // 处理未定义类型

    private final int value;
    private final String description;

    ConsultantEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 根据整数值获取对应的枚举实例
     *
     * @param value 数据库存储的整数值
     * @return 匹配的枚举实例，未匹配时返回UNKNOWN
     */
    public static ConsultantEnum fromValue(int value) {
        for (ConsultantEnum type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return UNKNOWN;
    }

    /**
     * 将枚举转换为数据库存储的整数值
     *
     * @param type 枚举实例
     * @return 对应的整数值
     */
    public static int toValue(ConsultantEnum type) {
        return type.getValue();
    }

    @Override
    public String toString() {
        return this.description;
    }
}