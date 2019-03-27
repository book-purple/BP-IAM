package com.bookpurple.iam.interceptor;

import com.bookpurple.iam.constant.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Created by gauravsharma on 2019-03-09.
 */
@Component
public class ClientAuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = Logger.getLogger(ClientAuthInterceptor.class);

    @Value("${basic.auth.token}")
    private String basicAuthToken;

    /**
     * This function intercept all open API requests
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object Handler
     * @return boolean true if authorization header value matches
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authToken = request.getHeader(Constants.SecurityConstants.AUTHORIZATION);
        if (basicAuthToken.equals(authToken)) {
            logger.error("--- Authorization Success ---");
            return true;
        }
        logger.error("--- Authorization failed ---");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}