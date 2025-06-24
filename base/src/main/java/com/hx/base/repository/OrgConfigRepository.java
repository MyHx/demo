package com.hx.base.repository;

import com.hx.base.dao.entity.OrgConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrgConfigRepository extends JpaRepository<OrgConfig, Integer> {

    OrgConfig findByOrgCode(String orgCode);

    @Query(value = "select c.* from apk_org_config c" +
            " inner join apk_device_info d on c.org_code = d.org_code" +
            " where d.device_sn = ?1 and status = 1 limit 1 ", nativeQuery = true)
    OrgConfig findByDeviceSn(String sn);
}
