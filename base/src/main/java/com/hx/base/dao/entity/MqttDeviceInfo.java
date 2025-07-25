package com.hx.base.dao.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class MqttDeviceInfo {

    private String address;//MQTT地址

    private String clientid;

    private ArrayList<String> otaTopics;

    private String password;

    private ArrayList<String> printTopics;

    private String username;

}
