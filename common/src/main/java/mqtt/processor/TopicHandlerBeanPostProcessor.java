package mqtt.processor;

import lombok.extern.slf4j.Slf4j;
import mqtt.ConcurrentMapFactory;
import mqtt.annotation.MqttTopicHandler;
import mqtt.entity.SubscribeMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Component
public class TopicHandlerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            if (method.isAnnotationPresent(MqttTopicHandler.class)) {
                MqttTopicHandler annotation = method.getDeclaredAnnotation(MqttTopicHandler.class);
                String[] topics = annotation.topics();

                if (topics == null || topics.length == 0) {
                    log.warn("Method {} in {} has no topics specified.", method.getName(), beanName);
                    return;
                }

                // 支持 1 个参数（payload）或 2 个参数（topic + payload）
                int paramCount = method.getParameterCount();
                if (paramCount < 1 || paramCount > 2) {
                    log.error("Method {} must have 1 or 2 parameters (e.g., String payload or String topic, String payload).", method.getName());
                    return;
                }

                // 注册每个主题对应的方法
                Arrays.stream(topics).forEach(topic -> {
                    log.info("Registering handler for topic: {}", topic);
                    SubscribeMethod subscribeMethod = SubscribeMethod.builder().bean(bean).method(method).build();
                    ConcurrentMapFactory.addMethod(topic, subscribeMethod);
                });
            }
        });

        return bean;
    }
}