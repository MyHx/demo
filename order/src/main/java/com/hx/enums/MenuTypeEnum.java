package com.hx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author hexian
 * @date 2021/6/11 11:41
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    SUB_MENU("SUB_MENU", 10, "有子菜单"),
    SEND_MODULE_MENU("SEND_MODULE_MENU", 20, "发送模块菜单"),
    JUMP_LINK_MENU("JUMP_LINK_MENU", 30, "跳转链接菜单");

    private String name;
    private int value;
    private String desc;


    public static MenuTypeEnum checkCode(int code) {
        for (MenuTypeEnum typeEnum : MenuTypeEnum.values()) {
            if (typeEnum.getValue() == code) {
                return typeEnum;
            }
        }
        return null;
    }
}
