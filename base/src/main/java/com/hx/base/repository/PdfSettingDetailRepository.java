package com.hx.base.repository;

import com.hx.base.dao.entity.PdfSettingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfSettingDetailRepository extends JpaRepository<PdfSettingDetail, Integer> {

    PdfSettingDetail findByModelTypeAndModule(String modelType, Integer module);

}
