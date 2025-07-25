package mqtt.handler;

import lombok.extern.slf4j.Slf4j;
import mqtt.ConcurrentMapFactory;
import mqtt.callback.MqttSubscribeCallback;
import mqtt.entity.SubscribeMethod;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 订阅终端上行消息
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "infra.mqtt.domain", havingValue = "device")
public class MqttSubscribeHandler {

    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public MessageHandler inHandle() {
        return message -> {
            try {
                String topic = null;
                MessageHeaders headers = message.getHeaders();
                if (!headers.isEmpty()) {
                    topic = String.valueOf(headers.get("mqtt_receivedTopic"));
                }
                if (topic == null) {
                    log.error("handler: topic is null");
                    return;
                }

                // 数据载荷
                Object payload = message.getPayload();

                // 先尝试查找是否有对应的回调接口
                MqttSubscribeCallback callback = ConcurrentMapFactory.getCallback(topic);
                if (callback != null) {
                    callback.messageArrived(topic, payload);
                    return;
                }

                // 再尝试查找是否有通过 @MqttTopicHandler 注解绑定的方法
                SubscribeMethod subscribeMethod = ConcurrentMapFactory.getMethod(topic);
                if (subscribeMethod != null) {
                    Object bean = subscribeMethod.getBean();
                    Method method = subscribeMethod.getMethod();
                    try {
                        method.setAccessible(true);
                        method.invoke(bean, topic, payload);
                    } catch (Exception e) {
                        log.error("bean[{}].method[{}] invoke exception: ", bean, method.getName(), e);
                    }
                    return;
                }

                log.warn("topic[{}]: 没有找到该topic对应的处理方法", topic);

            } catch (Exception e) {
                log.error("【南向接口指令,链路1异常】设备数据上报异常{}", e);
            }
        };
    }

    /**
     * 通过通道获取数据
     * <p>
     * ServiceActivator: 指定接收消息的管道为 mqttErrorChannel，投递到 mqttErrorChannel 管道中的消息会被该方法接收并执行
     *
     * @return MessageHandler
     */
    @Bean
    @ServiceActivator(inputChannel = "errorChannel")
    public MessageHandler errorHandler() {
        return message -> {
            log.error("errorHandler: {}", message);
        };
    }


}
