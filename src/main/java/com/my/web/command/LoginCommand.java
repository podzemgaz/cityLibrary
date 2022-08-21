package com.my.web.command;

import com.my.entity.Role;
import com.my.entity.User;

import com.my.logic.LoginLogic;
import com.my.util.ConfigManager;
import com.my.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req) {
        String page;

        String login = req.getParameter(Constants.PARAM_NAME_LOGIN);
        LOG.trace("Login in request: " + login);

        String password = req.getParameter(Constants.PARAM_NAME_PASSWORD);
        User user;

        if (((user = LoginLogic.checkLoginPass(login, password))) != null) {

            Role role = Role.getRole(user);

            HttpSession httpSession = req.getSession();

            httpSession.setAttribute("errorMessage", null);
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("role", role.toString());

            //set page to main.jsp
            page = ConfigManager.getProperty("path.page.main");
        } else {
            req.getSession().setAttribute("errorMessage", MessageManager.getProperty("message.err.login"));
            page = ConfigManager.getProperty("path.page.login");
        }

        return page;
    }
}
