package com.jingdong;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitBusyService implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        // 此处用来初始化项目启动要执行的方法，一般是一些缓存预热


    }
}
