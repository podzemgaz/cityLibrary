package com.my.web.filter;

import com.my.entity.Role;
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
import java.util.*;

@WebFilter("/*")
public class AccessFilter extends HttpFilter {
    private static final Logger LOG = LogManager.getLogger(AccessFilter.class);
    @Serial
    private static final long serialVersionUID = -2622026839940022444L;

    private final Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();

    @Override
    public void init() {

        accessMap.put(Role.ADMIN, asList("/users"));
        accessMap.put(Role.CLIENT, asList("/cabinet"));
        accessMap.put(Role.MODER, asList("/cabinet"));
        accessMap.put(null, asList("/login", "/registration"));
        commons = asList("/", "/controller");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        String path = ((HttpServletRequest) req).getServletPath();
        LOG.trace("servlet path: " + path);

        if (path.equals("/css/style.css") || path.matches(".*jsp")) {
            chain.doFilter(req, res);
        } else  if (commons.contains(path)) {
            chain.doFilter(req, res);
        } else {
            HttpSession session = ((HttpServletRequest)req).getSession();
            Role userRole = null;
            try {
                userRole = Role.valueOf(((String) session.getAttribute("role")).toUpperCase());
            } catch (NullPointerException ignored) {
            }

            LOG.debug("user Role: " + userRole);
            if (accessMap.get(userRole).contains(path)) {
                LOG.trace("access allowed");
                chain.doFilter(req, res);
            } else {
                LOG.trace("access denied");
                ((HttpServletResponse)res).sendRedirect(((HttpServletRequest) req).getContextPath());
            }
        }


    }

    private List<String> asList(String... str) {
        return new ArrayList<>(Arrays.asList(str));
    }
}
