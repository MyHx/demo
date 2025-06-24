package com.hx.base.service.impl;

import com.hx.base.dao.entity.OrgConfig;
import com.hx.base.dao.entity.PdfSetting;
import com.hx.base.dao.entity.PdfSettingDetail;
import com.hx.base.dao.entity.PdfSettingVO;
import com.hx.base.repository.OrgConfigRepository;
import com.hx.base.repository.PdfSettingDetailRepository;
import com.hx.base.repository.PdfSettingRepository;
import com.hx.base.service.PdfSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class PdfSettingServiceImpl implements PdfSettingService {

    private final PdfSettingRepository pdfSettingRepository;
    private final PdfSettingDetailRepository pdfSettingDetailRepository;
    private final OrgConfigRepository orgConfigRepository;

    @Override
    public PdfSettingVO getByOrgCodeAndModule(String orgCode, Integer module, String orgName, String orgImage) {
        OrgConfig orgConfig = orgConfigRepository.findByOrgCode(orgCode);
        if (orgConfig == null) {
            throw new RuntimeException("机构未适配打印模板！");
        }
        PdfSetting pdfSetting = pdfSettingRepository.findByModelType(orgConfig.getModelType());
        PdfSettingDetail pdfSettingDetail = pdfSettingDetailRepository.findByModelTypeAndModule(orgConfig.getModelType(), module);
        if (pdfSettingDetail == null) {
            throw new RuntimeException("未适配打印模板！");
        }
        String footerHtml = pdfSetting.getFooterHtml();
        footerHtml = footerHtml.replace("{{current_time}}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        pdfSetting.setFooterHtml(footerHtml);
        String headerHtml = pdfSetting.getHeaderHtml();
        headerHtml = headerHtml.replace("{{header_image}}", pdfSettingDetail.getHeaderImage());
        headerHtml = headerHtml.replace("{{header_orgname}}", orgName);
        headerHtml = headerHtml.replace("{{org_image}}", orgImage);
        pdfSetting.setHeaderHtml(headerHtml);

        PdfSettingVO vo = new PdfSettingVO();
        BeanUtils.copyProperties(pdfSetting, vo);
        vo.setHeaderImage(pdfSettingDetail.getHeaderImage());
        vo.setExplainImage(pdfSettingDetail.getExplainImage());
        vo.setDeepExplainImage(pdfSettingDetail.getDeepExplainImage());
        vo.setHealthImage(pdfSettingDetail.getHealthImage());
        return vo;
    }
}
