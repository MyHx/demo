package com.hx.base.service.impl;

import com.hx.base.dao.entity.Dic;
import com.hx.base.dao.entity.DicVo;
import com.hx.base.repository.DicRepository;
import com.hx.base.service.DicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class DicServiceImpl implements DicService {


    private final DicRepository dicRepository;
    @Override
    public List<DicVo> findDicList(String dicCode) {
        List<Dic> dicList = dicRepository.findByDicCode(dicCode);
        List<DicVo> dicVoList = new ArrayList<>();
        for (Dic dic : dicList) {
            DicVo dicVO = new DicVo();
            BeanUtils.copyProperties(dic, dicVO);
            dicVoList.add(dicVO);
        }
        return dicVoList;
    }

    @Override
    public Dic findOneByDicCode(String dicCode) {
        return dicRepository.findOneByDicCode(dicCode);
    }
}
