package com.my.web;

import com.my.util.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Serial;

@WebServlet(urlPatterns = {"/login", "/main", "/registration", "/users"})
public class Mapper extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(Mapper.class);
    @Serial
    private static final long serialVersionUID = 6406178475324484269L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path =req.getServletPath();
        LOG.trace("servlet path: " + path);

        switch (path) {
            case "/main" -> getForward(req, resp, ConfigManager.getProperty("path.page.main"));
            case "/login" -> getForward(req, resp, ConfigManager.getProperty("path.page.login"));
            case "/registration" -> getForward(req, resp, ConfigManager.getProperty("path.page.register"));
            case "/users" -> getForward(req, resp, ConfigManager.getProperty("path.page.users"));
        }
    }

    private static void getForward(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
