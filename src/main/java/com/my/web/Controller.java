package com.my.web;

import java.io.*;

import com.my.util.ConfigManager;
import com.my.util.MessageManager;
import com.my.web.command.ActionCommand;
import com.my.web.command.ActionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet( value = {"/controller", "/jsp/controller"})
public class Controller extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger(Controller.class);
    @Serial
    private static final long serialVersionUID = 1911492791029217609L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LOG.info("Controller#doGet");
        /*LOG.info("login: " + request.getParameter("login"));
        LOG.info("password: " + request.getParameter("password"));*/
        String page = process(req, res);
        LOG.debug("go forward to page: " + page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LOG.info("Controller#doPost");
        /*String login = request.getParameter("login");
        LOG.info("login: " + login);
        LOG.info("password: " + request.getParameter("password").isBlank());

        try {
            LOG.info(UserLogic.getUser(login));
        } catch (AppException e) {
            LOG.info("AppExeption");
        }*/
        String page = process(req, res);
        LOG.debug("Send redirect to page: " + page);
        page = getServletContext().getContextPath() + page;
        res.sendRedirect(page);
    }

    private String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LOG.debug("Controller starts");
        String page;
        //define command from jsp
        ActionFactory actionFactory = new ActionFactory();
        ActionCommand command = actionFactory.defineCommand(req);

        page = command.execute(req);


        /*
        *
        * */

        if (page == null) {
            LOG.debug("Send redirect to index page");
            //set error page
            page = ConfigManager.getProperty("path.page.index");
            req.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            res.sendRedirect(req.getContextPath() + page);
        }

        return page;
    }

}