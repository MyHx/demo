package mqtt.callback;

public interface MqttSubscribeCallback {
    /**
     * 处理消息
     *
     * @param topic   订阅主题
     * @param payload 负载数据
     */
    void messageArrived(String topic, Object payload);}
