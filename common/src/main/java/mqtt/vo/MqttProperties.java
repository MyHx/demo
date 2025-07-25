package mqtt.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * mqtt客户端连接
 */
@Data
@Component
@ConfigurationProperties(prefix = "infra.mqtt")
public class MqttProperties {

    /**
     * 领域 device、
     */
    private String domain;

    /**
     * 客户端ID
     */
    private String clientId;

    private Integer qos;

    /**
     * MQTT地址
     */
    private String[] serverURIs;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 订阅主题
     */
    private List<String> topics;

    

}
