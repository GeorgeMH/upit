package io.upit.jaxrs.guice;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Names;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestSessionFilter implements Filter {

    public static final String AUTH_SESSION_ID_COOKIE_NAME = "authSessionId";

    @Inject
    public RequestSessionFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Cookie cookie = null;
        if(null != httpRequest.getCookies() && httpRequest.getCookies().length > 0)
        for (Cookie theCookie : httpRequest.getCookies()) {
            if (AUTH_SESSION_ID_COOKIE_NAME.equals(theCookie.getName())) {
                cookie = theCookie;
                break;
            }
        }

        String authSessionId = null;

        if (null != cookie) {
            authSessionId = cookie.getValue();
        }

        httpRequest.setAttribute(Key.get(String.class, Names.named("AuthSessionId")).toString(), authSessionId);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
