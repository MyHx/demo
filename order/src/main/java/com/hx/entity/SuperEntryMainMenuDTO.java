package com.hx.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hexian
 * @date 2021/6/17 18:53
 */
@Data
public class SuperEntryMainMenuDTO {

    /**
     * 主菜单ID
     */
    @NotNull(message = "主菜单ID不能为空")
    private Long menuId;

    /**
     * 顺序
     */
    @NotNull(message = "主菜单顺序不能为空")
    private Integer order;
}
