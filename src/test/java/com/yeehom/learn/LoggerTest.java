package com.yeehom.learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yFoo on 13/01/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@Slf4j
public class LoggerTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    private static  String testStr = "test";

    @Test
    public void test1() {
//        String name = "imooc";
//        String password = "123456";
//        log.info("name:" + name + "password" + password);
//        log.info("name: {}, password: {}", name, password);
//        log.debug("debug...");
//        log.info("info...");
//        log.error("error..");
        logger.debug("debug...");
        logger.info("info...");
        logger.error("error...");
    }
}
