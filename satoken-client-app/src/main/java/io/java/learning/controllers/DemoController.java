package io.java.learning.controllers;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.sso.SaSsoManager;
import cn.dev33.satoken.sso.processor.SaSsoClientProcessor;
import cn.dev33.satoken.sso.template.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    // 首页 m2
    @RequestMapping("/")
    public String index山() {
        String str = "<h2>Sa-Token SSO-Client 应用端, M2 URL重定向跳转</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='/sso/login?back=' + encodeURIComponent(location.href);\">登录</a> " +
                "<a href='/sso/logout?back=self'>注销</a></p>";
        return str;
    }

    @RequestMapping("/m1")
    public String indexm1() {
        String authUrl = SaSsoManager.getClientConfig().splicingAuthUrl();
        String solUrl = SaSsoManager.getClientConfig().splicingSloUrl();
        String str = "<h2>Sa-Token SSO-Client 应用端 M1 共享Cookie</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='" + authUrl + "?mode=simple&redirect=' + encodeURIComponent(location.href);\">登录</a> " +
                "<a href=\"javascript:location.href='" + solUrl + "?back=' + encodeURIComponent(location.href);\">注销</a> </p>";
        return str;
    }

    /*
     * SSO-Client端：处理所有SSO相关请求
     *         http://{host}:{port}/sso/login          -- Client端登录地址，接受参数：back=登录后的跳转地址
     *         http://{host}:{port}/sso/logout         -- Client端单点注销地址（isSlo=true时打开），接受参数：back=注销后的跳转地址
     *         http://{host}:{port}/sso/logoutCall     -- Client端单点注销回调地址（isSlo=true时打开），此接口为框架回调，开发者无需关心
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoClientProcessor.instance.dister();
    }
    @RequestMapping("/redis")
    public String testRedis() {
        SaManager.getSaTokenDao().set("name", "value", 100000);
        return "OK";
    }

    // 查询我的账号信息
    @RequestMapping("/sso/myInfo")
    public Object myInfo() {
        // 组织请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("apiType", "userinfo");
        map.put("loginId", StpUtil.getLoginId());

        // 发起请求
        Object resData = SaSsoUtil.getData(map);
        System.out.println("sso-server 返回的信息：" + resData);
        return resData;
    }
    @RequestMapping(value = "/demo/test", produces = "application/json;charset=UTF-8")
    public void test(@RequestBody String bodys) {
        System.out.println("bodys=" +bodys);
    }
}
