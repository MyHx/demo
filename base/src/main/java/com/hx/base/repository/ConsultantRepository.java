package com.hx.base.repository;



import com.hx.base.dao.entity.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultantRepository extends JpaRepository<Consultant, Integer> {

    List<Consultant> findByOrgCodeOrderBySortAsc(String uid);
}
