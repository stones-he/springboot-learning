
### 此应用是示例了一个通过实现一个服务为Prometheus提供动态把被监视的应用动态注册到Prometheus的实现示例

> 通过Prometheus的服务发现机制来实现动态注册被监视的应用到Prometheus
> 直接在prometheus配置文件`/etc/prometheus/prometheus.yml`中添加如下配置
> 

```yaml
scrape_configs:
  - job_name: "prometheus-sd"
    metrics_path: "/actuator/prometheus"
    http_sd_configs:
      - url: http://10.60.5.34:9009/prom/servers
        refresh_interval: 1m
```
