package com.hx.base.controller;

import com.hx.base.dao.entity.CorpSalon;
import com.hx.base.service.CorpSalonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hexian
 * @date 2022/3/28 14:49
 */
@Slf4j
@RestController
@RequestMapping("/salon")
public class CorpSalonController {

    @Autowired
    private CorpSalonService corpSalonService;

    @GetMapping("/testSalon/{id}")
    public CorpSalon testSalon(@PathVariable Long id) {
        return corpSalonService.selectByPrimaryKey(id);
    }
}
