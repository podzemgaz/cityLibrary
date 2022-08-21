package com.my.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;

@WebFilter("*.jsp")
public class JspFilter extends HttpFilter {
    private static final Logger LOG = LogManager.getLogger(JspFilter.class);
    @Serial
    private static final long serialVersionUID = -6659101668278803502L;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException {
        String servlet = ((HttpServletRequest)req).getServletPath();
        LOG.trace("servlet path: " + servlet);
        String way = req.getServletContext().getContextPath();
        LOG.trace("way: " + way);
        ((HttpServletResponse)res).sendRedirect(way);
    }
}
