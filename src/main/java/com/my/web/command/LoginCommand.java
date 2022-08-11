package com.my.web.command;

import com.my.entity.Role;
import com.my.entity.User;

import com.my.logic.LoginLogic;
import com.my.logic.UserLogic;
import com.my.util.ConfigManager;
import com.my.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand{
    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    public static final String PARAM_NAME_LOGIN = "login";
    public static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String page;

        String login = req.getParameter(PARAM_NAME_LOGIN);
        LOG.trace("Login in request: " + login);

        String password = req.getParameter(PARAM_NAME_PASSWORD);

        if (LoginLogic.checkLogin(login, password)) {

            User user = UserLogic.getUser(login);
            Role role = Role.getRole(user);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("role", role);

            //set page to main.jsp
            page = ConfigManager.getProperty("path.page.main");
        } else {
            req.setAttribute("errorLoginMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigManager.getProperty("path.page.index");
        }

        return page;
    }
}
