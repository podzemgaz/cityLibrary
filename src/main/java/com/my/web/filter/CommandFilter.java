package com.my.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;


@WebFilter("/controller")
public class CommandFilter extends HttpFilter {
        private static final Logger LOG = LogManager.getLogger(CommandFilter.class);
    @Serial
    private static final long serialVersionUID = -6280926500084615617L;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        LOG.debug("starts");
        chain.doFilter(req, res);
    }
}
