package com.hx.base.repository;


import com.hx.base.dao.entity.PdfSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfSettingRepository extends JpaRepository<PdfSetting, Integer> {

    PdfSetting findByModelType(String modelType);
}
