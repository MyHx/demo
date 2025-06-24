package com.hx.base.service;

import com.hx.base.dao.entity.Dic;
import com.hx.base.dao.entity.DicVo;

import java.util.List;

public interface DicService {
    List<DicVo> findDicList(String dicCode);

    Dic findOneByDicCode(String dicCode);
}
