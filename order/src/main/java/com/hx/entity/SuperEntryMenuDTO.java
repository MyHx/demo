package com.hx.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hx.enums.MenuTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 主菜单实体
 *
 * @author hexian
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperEntryMenuDTO {

    /**
     * 主菜单ID
     */
    private Long menuId;

    /**
     * 主菜单标题
     */
    @NotBlank(message = "主菜单标题不能为空")
    private String title;


    /**
     * 主菜单副标题
     */
    @NotBlank(message = "主菜单副标题不能为空")
    private String subTitle;


    /**
     * 主菜单图标url
     */
    @NotBlank(message = "主菜单图标不能为空")
    private String icon;

    /**
     * 主菜单类型[10表示有子菜单、20发送模块菜单、30跳转链接菜单]
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
     * 子菜单
     */
    private List<SuperEntrySubMenuDTO> subMenuList;
}