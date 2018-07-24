package com.referazi.security;

import com.referazi.chat.ChatUtils;
import com.referazi.dao.*;
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

public class ChatSecurityFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ChatSecurityFilter.class);

    private SessionProvider sessionProvider;

    private SecurityDao securityDao;

    private UserDao userDao;

    private ConversationDao conversationDao;

    private HistoryDao historyDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionProvider = (SessionProvider) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("sessionProvider");

        securityDao = (SecurityDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("securityDao");

        userDao = (UserDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("userDao");

        conversationDao = (ConversationDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("conversationDao");

        historyDao = (HistoryDao) WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext())
                .getBean("historyDao");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getParameter("authorization");

        ChatUtils.setAllDao(conversationDao, historyDao, userDao);

        Auth auth = (Auth) sessionProvider.getSession(token);

        if(auth == null){
            auth = securityDao.getAuthDetailsByToken(token);
        }

        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        Long currentTime = utc.toEpochSecond();


        if (auth == null) {
            log.debug("User not found");
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

    @Override
    public void destroy() {

    }
}
