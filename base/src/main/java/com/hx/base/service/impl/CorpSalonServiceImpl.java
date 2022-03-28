package com.hx.base.service.impl;

import com.hx.base.dao.entity.CorpSalon;import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hx.base.dao.mapper.CorpSalonMapper;
import com.hx.base.service.CorpSalonService;

/**
 * @author hexian
 * @date 2022/3/28 14:47
 */
@Service
public class CorpSalonServiceImpl implements CorpSalonService {

    @Resource
    private CorpSalonMapper corpSalonMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return corpSalonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CorpSalon record) {
        return corpSalonMapper.insert(record);
    }

    @Override
    public int insertSelective(CorpSalon record) {
        return corpSalonMapper.insertSelective(record);
    }

    @Override
    public CorpSalon selectByPrimaryKey(Long id) {
        return corpSalonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CorpSalon record) {
        return corpSalonMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CorpSalon record) {
        return corpSalonMapper.updateByPrimaryKey(record);
    }
}


