package mqtt.vo;

import lombok.Data;

/**
 * 加密配置
 **/
@Data
public class EncryptProperties {

    private String key;

    private String salt;

}
