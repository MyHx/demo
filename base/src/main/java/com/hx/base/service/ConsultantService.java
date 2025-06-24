package com.hx.base.service;


import com.hx.base.dao.entity.Consultant;
import com.hx.base.dao.entity.ConsultantVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public interface ConsultantService {

    /**
     * 获取顾问信息
     *
     * @param orgCode 机构编码
     * @return 顾问信息列表
     */
    List<ConsultantVO> getConsultantInfo(@NotBlank String orgCode);

    Consultant getConsultantInfoById(Integer id);
}
