package mqtt;


import mqtt.callback.MqttSubscribeCallback;
import mqtt.entity.SubscribeMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储 MQTT 主题与回调方法的映射关系
 */
public class ConcurrentMapFactory {
    private static final Map<String, SubscribeMethod> METHOD_MAP = new ConcurrentHashMap<>();
    private static final Map<String, MqttSubscribeCallback> CALLBACK_MAP = new ConcurrentHashMap<>();

    public static void addMethod(String topic, SubscribeMethod method) {
        METHOD_MAP.put(topic, method);
    }

//    public static SubscribeMethod getMethod(String topic) {
//        return METHOD_MAP.get(topic);
//    }

    // 示例：使用 Paho 的 MqttTopic.isMatched 实现通配符匹配
    public static SubscribeMethod getMethod(String topic) {
        for (Map.Entry<String, SubscribeMethod> entry : METHOD_MAP.entrySet()) {
            String registeredTopic = entry.getKey();
            if (isTopicMatched(registeredTopic, topic)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static boolean isTopicMatched(String filter, String topic) {
        if (filter.equals(topic)) return true;
        if ("#".equals(filter)) return true;

        String[] filterSegments = filter.split("/");
        String[] topicSegments = topic.split("/");

        int fLen = filterSegments.length;
        int tLen = topicSegments.length;

        // 如果 filter 是类似 a/b/c#，且长度大于等于实际 topic，继续判断
        if (filter.endsWith("#")) {
            String prefix = filter.substring(0, filter.length() - 2); // 去掉 # 及前面的 /
            return topic.startsWith(prefix);
        }

        if (fLen != tLen) return false;

        for (int i = 0; i < fLen; i++) {
            String fSeg = filterSegments[i];
            if (fSeg.equals("+")) continue;
            if (!fSeg.equals(topicSegments[i])) return false;
        }

        return true;
    }


    public static void addCallback(String topic, MqttSubscribeCallback callback) {
        CALLBACK_MAP.put(topic, callback);
    }

    public static MqttSubscribeCallback getCallback(String topic) {
        return CALLBACK_MAP.get(topic);
    }
}

