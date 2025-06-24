package com.hx.base.service;


import com.hx.base.dao.entity.OrgConfigVO;

/**
 * Description:
 *
 * @Author 唐立志
 * @Create 2025/1/13 17:44
 * @Version 1.0
 */
public interface ConfigService {

    OrgConfigVO getOrgConfigByOrgCode(String orgCode);
}
