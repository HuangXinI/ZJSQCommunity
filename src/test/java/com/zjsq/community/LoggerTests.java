package com.zjsq.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest//测试
@ContextConfiguration(classes = CommunityApplication.class)//设置，以这个类为配置类
public class LoggerTests {

    private static final Logger logger = LoggerFactory.getLogger(Logger.class);

    @Test
    public void testLogger(){
        System.out.println(logger.getName());

        logger.debug("debugLog");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

    }
}
