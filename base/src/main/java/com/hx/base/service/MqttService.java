package com.hx.base.service;


import com.hx.base.dao.entity.MqttDeviceInfo;

public interface MqttService {

    MqttDeviceInfo getMqttDeviceInfo(String clientid);
}
