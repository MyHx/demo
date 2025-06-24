package com.hx.base.service;

import com.hx.base.dao.entity.PromptDic;
import com.hx.base.dao.entity.PromptTemplate;

import java.util.List;

/**
 * Description:
 *
 * @Author 唐立志
 * @Create 2025/2/8 11:20
 * @Version 1.0
 */
public interface PromptService {

    PromptTemplate getPromptTemplate(Integer module, String type);

}
