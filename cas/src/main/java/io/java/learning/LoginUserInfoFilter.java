package io.java.learning;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LoginUserInfoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String loginName = CasUtils.getLoginNameFromCas((HttpServletRequest) request);
        if (loginName != null) {
            System.out.println("loginName: " + loginName);
            ((HttpServletRequest) request).getSession().setAttribute("loginName", loginName);
        }
        chain.doFilter(request, response);
    }
}
