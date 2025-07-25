package mqtt.vo;

import lombok.Data;

/**
 * 延迟消息配置
 *
 * @Author: sun.wei
 * @since 2024年11月18日
 **/
@Data
public class DelayProperties {

    //最大重复次数
    private Integer maxRepeat = 10;

    //终端队列
    private Boolean terminalQueue = false;

    //回调是否开启
    private Boolean isCallback;

    //消息延迟最小秒发送
    private Integer minRange = 10;

    //消息延迟最大秒发送
    private Integer maxRange = 60;

//    //及时通讯队列
//    private Boolean imQueue = false;

}
