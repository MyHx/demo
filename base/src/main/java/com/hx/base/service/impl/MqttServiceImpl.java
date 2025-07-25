package com.hx.base.service.impl;

import com.hx.base.dao.entity.MqttDeviceInfo;
import com.hx.base.service.MqttService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mqtt.vo.InfraProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;


@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class MqttServiceImpl implements MqttService {

    private final InfraProperties infraProperties;

    @Override
    public MqttDeviceInfo getMqttDeviceInfo(String clientid) {
        String[] serverURIs = infraProperties.getMqtt().getServerURIs();
        String address = serverURIs[0].split("//")[1];

        ArrayList<String> printTopics = new ArrayList<>();
        printTopics.add("skyAi/broadcast");
        printTopics.add("skyAi/device/" + clientid);

        //创建mqtt_user
        try {
//            MqttUser mu = mqttUserRepository.findByUsername(clientid);
//            if (null == mu) {
//                MqttUser newMu = new MqttUser();
//                newMu.setUsername(clientid);
//                newMu.setPasswordHash("44edc2d57cde8d79c98145003e105b90a14f1460b79186ea9cfe83942fc5abb5");
//                newMu.setSalt("slat_foo123");
//                newMu.setIsSuperuser(0);
//                newMu.setCreated(new Date());
//                newMu.setPassword("public");
//                mqttUserRepository.save(newMu);
//            }
        } catch (Exception e) {
            log.error("创建mqtt_user...操作失败！clientid:{}", clientid);
            throw new RuntimeException(e);
        }
        return MqttDeviceInfo.builder().clientid(clientid).address(address).username(clientid).password("public").printTopics(printTopics).build();
    }
}
