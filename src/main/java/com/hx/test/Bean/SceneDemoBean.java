package com.hx.test.Bean;

import cn.hutool.core.map.MapUtil;
import com.hx.test.server.SceneServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SceneDemoBean implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, SceneServer> beansOfType = applicationContext.getBeansOfType(SceneServer.class);
        if (MapUtil.isNotEmpty(beansOfType)) {
            for (Map.Entry<String, SceneServer> stringDemoServerEntry : beansOfType.entrySet()) {
                System.out.println(stringDemoServerEntry.getKey());
                System.out.println(stringDemoServerEntry.getValue());
            }
            System.out.println("项目启动执行afterPropertiesSet()方法");
        }
    }
}
