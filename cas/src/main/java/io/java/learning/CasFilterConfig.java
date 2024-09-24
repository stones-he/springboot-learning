package io.java.learning;

import org.apereo.cas.client.authentication.AuthenticationFilter;
import org.apereo.cas.client.session.SingleSignOutFilter;
import org.apereo.cas.client.session.SingleSignOutHttpSessionListener;
import org.apereo.cas.client.util.HttpServletRequestWrapperFilter;
import org.apereo.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class CasFilterConfig {
    @Value("${cas.server-url-prefix}")
    private String CAS_URL;
    @Value("${cas.client-host-url}")
    private String APP_URL;

    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> listener = new ServletListenerRegistrationBean<>();
        listener.setListener(new SingleSignOutHttpSessionListener());
        listener.setOrder(Ordered.HIGHEST_PRECEDENCE);
        listener.setEnabled(true);
        return listener;
    }

    @Bean
    public FilterRegistrationBean<SingleSignOutFilter> singleSignOutFilter() {
        FilterRegistrationBean<SingleSignOutFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new SingleSignOutFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("casServerUrlPrefix", CAS_URL );
        filter.setName("CAS Single Sign Out Filter");
        filter.setOrder(2);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> AuthenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new AuthenticationFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("casServerLoginUrl", CAS_URL + "/login");
        filter.addInitParameter("serverName", APP_URL);
        filter.setName("CAS Filter");
        filter.setOrder(3);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> cas30ProxyReceivingTicketValidationFilter () {
        FilterRegistrationBean<Cas30ProxyReceivingTicketValidationFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        filter.addUrlPatterns("/*");
        filter.addInitParameter("casServerUrlPrefix", CAS_URL);
        filter.addInitParameter("serverName", APP_URL);
        filter.setName("CAS Validation Filter");
        filter.setOrder(4);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<HttpServletRequestWrapperFilter> httpServletRequestWrapperFilter() {
        FilterRegistrationBean<HttpServletRequestWrapperFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new HttpServletRequestWrapperFilter());
        filter.addUrlPatterns("/*");
        filter.setName("CAS HttpServletRequest Wrapper Filter");
        filter.setOrder(5);
        return filter;
    }

    @Bean
    public FilterRegistrationBean<LoginUserInfoFilter> loginUserInfoFilter() {
        FilterRegistrationBean<LoginUserInfoFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new LoginUserInfoFilter());
        filter.addUrlPatterns("/*");
        filter.setName("Login User Info Filter");
        filter.setOrder(6);
        return filter;
    }

}
