package com.referazi.security;

import com.referazi.dao.SecurityDao;
import com.referazi.dao.UserDao;
import com.referazi.models.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class SecurityFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    private SessionProvider sessionProvider;

    private SecurityDao securityDao;

    private UserDao userDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionProvider = (SessionProvider) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("sessionProvider");

        securityDao = (SecurityDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("securityDao");

        userDao = (UserDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("userDao");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getRequestURI().endsWith("auth/login") || request.getRequestURI().endsWith("user/register") ||
                request.getRequestURI().endsWith("auth/resetPassword") || request.getRequestURI().endsWith("auth/changePassword") ||
                request.getRequestURI().endsWith("auth/savePassword")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String token = request.getHeader("authorization");

            Auth auth = (Auth) sessionProvider.getSession(token);

            if(auth == null){
                auth = securityDao.getAuthDetailsByToken(token);
            }

            ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
            Long currentTime = utc.toEpochSecond();


            if (auth == null) {
                log.debug("Invalid Token");
                response.sendError(401, "unauthorized");
            } else if (auth.getExpiresAt().getTime() < currentTime*1000) {
                sessionProvider.removeSession(token);
                securityDao.deleteUserToken(auth.getUserId(), token);
                log.debug("Token Expired");
                response.sendError(401, "unauthorized");
            } else {
                log.debug("Authorization successfull for: " + auth.getUserId());
                auth.setUser(userDao.findUserById(auth.getUserId()));
                sessionProvider.addSession(token, auth);
                SecurityUtils.setUserAuthInfo(auth);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
