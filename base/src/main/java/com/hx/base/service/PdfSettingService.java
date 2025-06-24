package com.hx.base.service;


import com.hx.base.dao.entity.PdfSettingVO;
import org.springframework.stereotype.Service;

public interface PdfSettingService {

    PdfSettingVO getByOrgCodeAndModule(String orgCode, Integer module, String orgName, String orgImage);

}