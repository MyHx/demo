package mqtt.entity;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;

@Builder
@Data
public class SubscribeMethod {
    private Object bean;
    private Method method;
}
