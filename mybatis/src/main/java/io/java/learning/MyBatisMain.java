package io.java.learning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.java.learning.mapper")
/**
 * Spring Boot程序的入口类
 */
public class MyBatisMain {
    /**
     * 启动Spring Boot程序的唯一入口
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(MyBatisMain.class, args);
    }

    /**
     * 注册一个钩子函数，当程序退出时，执行一些操作
     * 1. 释放资源
     * 2. 保存数据
     * 3. 发送日志
     * 4. 发送邮件
     * 5. ...
     */
    public void shutdonwHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutting down...");
            }
        });
    }

}