package com.hx.base.service.impl;


import com.hx.base.constant.ConsultantEnum;
import com.hx.base.dao.entity.Consultant;
import com.hx.base.dao.entity.ConsultantVO;
import com.hx.base.repository.ConsultantRepository;
import com.hx.base.service.ConsultantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class ConsultantServiceImpl implements ConsultantService {

    private final ConsultantRepository consultantRepository;

    @Override
    public List<ConsultantVO> getConsultantInfo(String orgCode) {
        List<Consultant> consultantList = consultantRepository.findByOrgCodeOrderBySortAsc(orgCode);
        return consultantList.stream().map(base -> {
            ConsultantVO vo = new ConsultantVO();
            BeanUtils.copyProperties(base, vo);
            vo.setTypeName(ConsultantEnum.fromValue(base.getType()).getDescription());
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public Consultant getConsultantInfoById(Integer id) {
        if (null == id) {
            log.error("getConsultantInfoById.id为空");
            return null;
        }
        return consultantRepository.findById(id).orElse(null);
    }
}
