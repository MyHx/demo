package com.hx.base.service.impl;

import com.hx.base.dao.entity.OrgConfig;
import com.hx.base.dao.entity.OrgConfigVO;
import com.hx.base.repository.OrgConfigRepository;
import com.hx.base.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final OrgConfigRepository orgConfigRepository;
    private static String defaultImage = "data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iNCIgaGVpZ2h0PSIzNiIgdmlld0JveD0iMCAwIDQgMz" +
            "YiIGZpbGw9Im5vbmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+CjxnIGlkPSImIzIzMTsmIzE2OTsmIzE4NjsmIzIzMTsmIzE1Mzsm" +
            "IzE4OTsmIzIyOTsmIzE1NTsmIzE5MDsiPgo8cmVjdCBpZD0iJiMyMzE7JiMxNjk7JiMxODY7JiMyMzE7JiMxNTM7JiMxODk7JiMyMjk7JiMxNTU7JiMxOTA" +
            "7XzIiIHdpZHRoPSI0IiBoZWlnaHQ9IjM2IiBmaWxsPSJ3aGl0ZSIvPgo8L2c+Cjwvc3ZnPgo=";
    @Override
    public OrgConfigVO getOrgConfigByOrgCode(String orgCode) {
        OrgConfig orgConfig = orgConfigRepository.findByOrgCode(orgCode);
        OrgConfigVO orgConfigVO = new OrgConfigVO();
        if (orgConfig != null) {
            BeanUtils.copyProperties(orgConfig, orgConfigVO);
        }
        else {
            orgConfigVO.setOrgCode(orgCode);
            orgConfigVO.setOrgName("");
            orgConfigVO.setShowName("");
            orgConfigVO.setOrgImage(defaultImage);
        }
        return orgConfigVO;
    }
}
