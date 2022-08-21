package com.my.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;


@WebFilter("/")
public class RootFilter extends HttpFilter {
    private static final Logger LOG = LogManager.getLogger(RootFilter.class);
    @Serial
    private static final long serialVersionUID = 2837878491152053811L;


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        /*LOG.debug("start filter catching jsp");
        String servletPath = ((HttpServletRequest) req).getServletPath();
        String path = getServletContext().getContextPath();
        LOG.debug("context path --> " + path);
        LOG.trace("servletPath: " + servletPath);
        if (!servletPath.equals("/index.jsp"))
            ((HttpServletResponse)res).sendRedirect(path);
        else
            chain.doFilter(req, res);*/

        /*String servletPath = ((HttpServletRequest)req).getServletPath();
        LOG.trace("servletPath: " + servletPath);

        HttpSession session = ((HttpServletRequest) req).getSession();
        User user = (User)session.getAttribute("user");
        Role role = (Role)session.getAttribute("role");

        LOG.trace("User in session: " + user);
        LOG.trace("Role in session: " + role);
        ((HttpServletResponse)res).sendRedirect(getServletContext().getContextPath() + "/controller");*/

        /*LOG.debug("Filter starts");
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        LOG.trace("Session: " + session);
        if (session != null) {
            LOG.trace("Set errorMessage null");
            session.setAttribute("errorMessage", null);
        }

        LOG.debug("Filter finish");
        chain.doFilter(req, res);*/

        String servlet = ((HttpServletRequest)req).getServletPath();
        LOG.trace("servlet path: " + servlet);
        String way = "/main";
        LOG.trace("way: " + way);
        req.getRequestDispatcher(way).forward(req, res);


    }
}
