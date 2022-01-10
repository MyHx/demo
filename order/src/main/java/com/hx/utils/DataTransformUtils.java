package com.hx.utils;

import com.alibaba.fastjson.JSON;
import com.hx.entity.SuperEntryMenuDTO;
import com.hx.entity.SuperEntryMenuDTO_COPY;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * List转换
 *
 * @author hexian
 */
public class DataTransformUtils {

    /**
     * 通过Copy方式转换
     *
     * @param list 源
     * @return 目标List
     */
    public static List<SuperEntryMenuDTO_COPY> transformListByCopy(List<SuperEntryMenuDTO> list) {
        List<SuperEntryMenuDTO_COPY> voList = new ArrayList<>();
        for (SuperEntryMenuDTO model : list) {
            SuperEntryMenuDTO_COPY vo = new SuperEntryMenuDTO_COPY();
            BeanUtils.copyProperties(model, vo);
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 通过Json方式转换
     *
     * @param list 源
     * @return 目标List
     */
    public static List<SuperEntryMenuDTO_COPY> transformListByJson(List<SuperEntryMenuDTO> list) {
        String s = JSON.toJSONString(list);
        return JSON.parseArray(s, SuperEntryMenuDTO_COPY.class);
    }


}
