package cn.imust.ys.springbootshiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class HeaderHttpSessionStrategy implements HttpSessionStrategy {
    private String headerName = "x-auth-token";

    public String getRequestedSessionId(HttpServletRequest request) {
        return request.getHeader(headerName);
    }

    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(headerName, session.getId());
    }

    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(headerName, "");
    }

    /**
     * The name of the header to obtain the session id from. Default is "x-auth-token".
     * @param headerName the name of the header to obtain the session id from.
     */
    public void setHeaderName(String headerName) {
//        Assert.notNull(headerName, "headerName cannot be null");
        this.headerName = headerName;
    }
}
