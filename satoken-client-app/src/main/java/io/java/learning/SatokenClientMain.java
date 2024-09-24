package io.java.learning;

import cn.dev33.satoken.sso.config.SaSsoClientConfig;
import com.dtflys.forest.Forest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SatokenClientMain {
    public static void main(String[] args) {
        SpringApplication.run(SatokenClientMain.class, args);
    }

    // 配置SSO相关参数
    @Autowired
    private void configSso(SaSsoClientConfig ssoClient) {
        // 配置Http请求处理器
        ssoClient.sendHttp = url -> {
            System.out.println("------ 发起请求：" + url);
            String resStr = Forest.get(url).executeAsString();
            System.out.println("------ 请求结果：" + resStr);
            return resStr;
        };
    }
}