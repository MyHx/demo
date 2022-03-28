package com.hx.base.service;

    import com.hx.base.dao.entity.CorpSalon;

/**
 * @author hexian
 * @date 2022/3/28 14:47
 */
public interface CorpSalonService {


    int deleteByPrimaryKey(Long id);

    int insert(CorpSalon record);

    int insertSelective(CorpSalon record);

    CorpSalon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CorpSalon record);

    int updateByPrimaryKey(CorpSalon record);
}


