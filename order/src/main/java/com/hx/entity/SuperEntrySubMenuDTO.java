package com.hx.entity;

import com.hx.enums.MenuTypeEnum;
import lombok.Data;

/**
 * 子菜单实体
 *
 * @author hexian
 */
@Data
public class SuperEntrySubMenuDTO {
    /**
     * 子菜单标题
     */
    private String title;

    /**
     * 子菜单副标题
     */
    private String subTitle;

    /**
     * 子菜单图标url
     */
    private String icon;

    /**
     * 子菜单类型[20发送模块菜单、30跳转链接菜单]
     */
    private MenuTypeEnum menuType;

    /**
     * 模块消息ID
     */
    private Long moduleId;


    /**
     * 模块对话文案
     */
    private String text;


    /**
     * 跳转功能类型
     */
    private Integer actionType;


    /**
     * 是否启用 0为启用，1为不启用
     */
    private Integer isEnable;

}