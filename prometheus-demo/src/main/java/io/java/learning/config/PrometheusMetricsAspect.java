package io.java.learning.config;

import io.micrometer.core.instrument.Metrics;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class PrometheusMetricsAspect {
    @Pointcut("execution(* io.java.learning..*.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object metricsCollection(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if(attr == null) return null;
        HttpServletRequest request = attr.getRequest();
        //
        String api = request.getServletPath();
        String method = request.getMethod();
        long timeMills = System.currentTimeMillis();
        LocalDate now = LocalDate.now();
        String[] tags = new String[6];
        tags[0] = "api";
        tags[1] = api;
        tags[2] = "method";
        tags[3] = method;
        tags[4] = "day";
        tags[5] = now.toString();
//        tags[6] = "";
//        tags[7] = "";
//        tags[8] = "";
//        tags[9] = "";

        //
        Metrics.counter("http_request_demo_all", tags).increment();
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            Metrics.counter("http_request_demo_err", tags).increment();
            throw e;
        } finally {
            long f = System.currentTimeMillis();
            long l = f - timeMills;
            Metrics.timer("http_request_demo_time", tags).record(l, TimeUnit.MILLISECONDS);
        }
        return obj;
    }
}
