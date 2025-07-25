package mqtt.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 基础设施配置
 */
@Component
@ConfigurationProperties(prefix = "infra")
@Data
public class InfraProperties {

    private MqttProperties mqtt;

    private EncryptProperties encrypt;

    private DelayProperties delay;

}
