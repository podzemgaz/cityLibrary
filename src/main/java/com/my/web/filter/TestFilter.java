package com.my.web.filter;

import com.my.entity.Role;
import com.my.entity.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;

@WebFilter("/jsp/*")
public class TestFilter extends HttpFilter {
    private static final Logger LOG = LogManager.getLogger(TestFilter.class);
    @Serial
    private static final long serialVersionUID = 2837878491152053811L;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException {
        /*LOG.debug("start filter catching jsp");
        String servletPath = ((HttpServletRequest) req).getServletPath();
        String path = getServletContext().getContextPath();
        LOG.debug("context path --> " + path);
        LOG.trace("servletPath: " + servletPath);
        if (!servletPath.equals("/index.jsp"))
            ((HttpServletResponse)res).sendRedirect(path);
        else
            chain.doFilter(req, res);*/

        String servletPath = ((HttpServletRequest)req).getServletPath();
        LOG.trace("servletPath: " + servletPath);

        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User)session.getAttribute("user");
        Role role = (Role)session.getAttribute("role");

        LOG.trace("User in session: " + user);
        LOG.trace("Role in session: " + role);
        ((HttpServletResponse)res).sendRedirect(getServletContext().getContextPath());
    }
}
