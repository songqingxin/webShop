package com.darknight.webShop.system.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/30.
 */
public class LonginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession();

        if(session != null && session.getAttribute("loginUser") != null) {
            Map loginUser = (Map)session.getAttribute("loginUser");
            if(loginUser.get("loginId") != null) {
                String loginId = loginUser.get("loginId").toString();
                if(StringUtils.isNotBlank(loginId)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }

        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String ctx = request.getContextPath();
        String targetUri = request.getRequestURI();
        response.sendRedirect(ctx + "/webShop/system/loginPage.jsp?targetUri=" + targetUri);
    }

    @Override
    public void destroy() {

    }
}