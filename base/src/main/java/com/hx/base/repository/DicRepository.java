package com.hx.base.repository;

import com.hx.base.dao.entity.Dic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lijiaqi
 * @version 1.0
 * @date 2021/8/9
 */
public interface DicRepository extends JpaRepository<Dic, Integer> {

    @Query(value = "select * from apk_dic where dic_code = ?1 order by sort_num", nativeQuery = true)
    List<Dic> findByDicCode(String dicCode);

    @Query(value = "select * from apk_dic where dic_code = ?1 limit 1", nativeQuery = true)
    Dic findOneByDicCode(String dicCode);

}