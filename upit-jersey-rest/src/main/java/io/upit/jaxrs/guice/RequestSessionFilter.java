package io.upit.jaxrs.guice;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Names;
import io.upit.UpitServiceException;
import io.upit.dal.models.AuthSession;
import io.upit.security.RequestSession;
import io.upit.services.AuthSessionService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestSessionFilter implements Filter {

    public static final String AUTH_SESSION_ID_COOKIE_NAME = "authSessionId";

    private final AuthSessionService authSessionService;

    @Inject
    public RequestSessionFilter(AuthSessionService authSessionService) {
        this.authSessionService = authSessionService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Cookie cookie = null;
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

        AuthSession session = null;

        if (null != authSessionId) {
            session = authSessionService.getById(authSessionId);
            if(null == session) {
                throw new ServletException("Unable to create anonymous AuthSession for request");
            }
        } else {
            try {
                session = authSessionService.createAnonymousAuthSession();
            } catch (UpitServiceException e) {
                throw new ServletException("Unable to create anonymous AuthSession for request", e);
            }
        }

        RequestSession requestSession = new RequestSession(session);

        httpRequest.setAttribute(Key.get(RequestSession.class, Names.named("RequestSession")).toString(), requestSession);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
