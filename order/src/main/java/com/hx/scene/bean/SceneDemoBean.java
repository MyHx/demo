package com.hx.scene.bean;

import cn.hutool.core.map.MapUtil;
import com.hx.scene.server.SceneServer;
import com.hx.scene.service.SceneOneBeamImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * 场景模式
 * @author hex
 */
@Component
public class SceneDemoBean {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Map<String, SceneServer> beansOfType = applicationContext.getBeansOfType(SceneServer.class);
        if (MapUtil.isNotEmpty(beansOfType)) {
            for (Map.Entry<String, SceneServer> stringDemoServerEntry : beansOfType.entrySet()) {
                System.out.println(stringDemoServerEntry.getKey());
                System.out.println(stringDemoServerEntry.getValue());
            }
            System.out.println("使用@PostConstruct注解项目启动执行init()方法");
        }
        //销毁一个名为Destroy的Bean
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Destroy.class);
        ctx.refresh();
        ctx.close();
    }




}


