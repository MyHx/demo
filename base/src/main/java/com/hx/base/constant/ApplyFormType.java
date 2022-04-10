package com.hx.base.constant;

/**
 * @author hexian
 * @date 2022/3/22 16:46
 */
public enum ApplyFormType {
    // 姓名
    NAME("姓名"),
    // 手机号
    PHONE("手机"),
    // 公司
    COMPANY("公司"),
    // 职位
    POSITION("职位"),

    ;

    private final String key;

    ApplyFormType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static ApplyFormType getApplyForm(String key) {
        ApplyFormType[] arr = ApplyFormType.values();
        for (ApplyFormType type : arr) {
            if (type.getKey().equalsIgnoreCase(key)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ApplyFormType{" +
                "key='" + key + '\'' +
                '}';
    }
}
