package com.hx.entity;

import lombok.Data;

/**
 * 基金持仓基金经理信息
 *
 * @author hexian
 * @date 2021/5/27 17:49
 */
@Data
public class FundPositionManagerInfoDTO {

    /**
     * 证券内部编码
     */
    private Integer innerCode;

    /**
     * 基金经理ID
     */
    private Integer managerId;

    /**
     * 基金经理姓名
     */
    private String managerName;

//    /**
//     * 基金经理职位名称 1-基金经理，2-基金经理助理
//     */
//    private Integer postName;

}
