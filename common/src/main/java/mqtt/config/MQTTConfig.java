package mqtt.config;

import lombok.extern.slf4j.Slf4j;
import mqtt.vo.InfraProperties;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@Slf4j
@ConditionalOnProperty(name = "infra.mqtt.domain", havingValue = "device")
public class MQTTConfig {
    @Resource
    private InfraProperties properties;
    private MqttPahoMessageDrivenChannelAdapter adapter;


    /**
     * 创建MqttPahoClientFactory，设置MQTT Broker连接属性，如果使用SSL验证，也在这里设置。
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        factory.setPersistence(null);
        return factory;
    }

    /**
     * 连接属性配置
     *
     * @return
     */
    private MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);

        options.setUserName(properties.getMqtt().getUserName());
        if (!StringUtils.isBlank(properties.getMqtt().getPassword()))
            options.setPassword(properties.getMqtt().getPassword().toCharArray());
        options.setServerURIs(properties.getMqtt().getServerURIs());
        // 设置会话心跳时间 单位为秒 服务器会每隔秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(30);
        // 开启mqtt重连
        options.setAutomaticReconnect(true);
        return options;
    }

    /**
     * 生产者通道
     *
     * @return
     */
    @Bean(name = "outputChannel")
    public MessageChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler outputHandler() {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(properties.getMqtt().getClientId() + "_out", mqttClientFactory());
        handler.setAsync(true);
        handler.setAsyncEvents(true);
        //设置转换器
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);
        handler.setConverter(converter);
        return handler;
    }

    /**
     * mqtt消费者
     *
     * @return
     */
    @Bean(name = "inputChannel")
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    /**
     * @return 消息通道 error
     */
    @Bean(name = "errorChannel")
    public MessageChannel errorChannel() {
        return new DirectChannel();
    }

    /**
     * 监听topic
     *
     * @return
     */
    @Bean
    public MessageProducer inBound() {
        if (adapter == null) {
            adapter = new MqttPahoMessageDrivenChannelAdapter(properties.getMqtt().getClientId(), mqttClientFactory());
        }
        List<String> topics = properties.getMqtt().getTopics();

        for (String topic : topics) {
            adapter.addTopic(topic);
        }
        adapter.setCompletionTimeout(15000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(properties.getMqtt().getQos());
        adapter.setOutputChannel(inputChannel());
        adapter.setErrorChannel(errorChannel());
        return adapter;
    }





















    /**
     * @return 注解 @MqClient 处理器
     */
//    @Bean
//    public ClientBeanPostProcessor clientBeanPostProcessor() {
//        return new ClientBeanPostProcessor(properties.getMqtt().getSubscribeTopic().toArray(new String[0]));
//    }
//
//    /**
//     * @return 注解 @MqSubscriber 处理器
//     */
//    @Bean
//    public SubscriberBeanPostProcessor subscriberBeanPostProcessor() {
//        return new SubscriberBeanPostProcessor(properties.getMqtt().getSubscribeTopic().toArray(new String[0]));
//    }


}
