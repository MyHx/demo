package com.hx.base.dao.mapper;

import com.hx.base.dao.entity.CorpSalon;

/**
 * @author hexian
 * @date 2022/3/28 12:20
 */
public interface CorpSalonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CorpSalon record);

    int insertSelective(CorpSalon record);

    CorpSalon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CorpSalon record);

    int updateByPrimaryKey(CorpSalon record);
}