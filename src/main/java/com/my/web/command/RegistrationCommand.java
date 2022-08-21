package com.my.web.command;

import com.my.entity.Role;
import com.my.entity.User;
import com.my.logic.RegisterLogic;

import com.my.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationCommand implements ActionCommand{
    private static final Logger LOG = LogManager.getLogger(RegistrationCommand.class);
    public static final String ERR_EMPTY_LOGIN_OR_PASSWORD = "Empty login or password";
    public static final String ERR_USER_EXISTS = "User with this login is registered already";
    public static final String ERR_DIFFERENT_PASSWORDS = "Different passwords";
    @Override
    public String execute(HttpServletRequest req) {
        String page;

        User user;

        LOG.trace("Set errorMessage: null");
        req.getSession().setAttribute("errorMessage", null);

        String login = req.getParameter(Constants.PARAM_NAME_LOGIN);
        LOG.trace("Login in request: " + login);
        String pass = req.getParameter(Constants.PARAM_NAME_PASSWORD);
        String confirmPass = req.getParameter("confirm_password");

        if (login.isEmpty() || pass.isEmpty()) {
            LOG.error(ERR_EMPTY_LOGIN_OR_PASSWORD);
            req.getSession().setAttribute("errorMessage", ERR_EMPTY_LOGIN_OR_PASSWORD);
            page = ConfigManager.getProperty("path.page.register");
        } else if (RegisterLogic.isLoginExist(login)) {
            LOG.error(ERR_USER_EXISTS);
            req.getSession().setAttribute("errorMessage", ERR_USER_EXISTS);
            page = ConfigManager.getProperty("path.page.register");
        } else if (!confirmPass.equals(pass)) {
            LOG.error(ERR_DIFFERENT_PASSWORDS);
            req.getSession().setAttribute("errorMessage", ERR_DIFFERENT_PASSWORDS);
            page = ConfigManager.getProperty("path.page.register");
        } else if ( ((user = RegisterLogic.insertUser(login, pass))) != null){
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("role", Role.getRole(user).toString());
            page = ConfigManager.getProperty("path.page.main");
        } else {
            req.getSession().setAttribute("errorMessage", "Something wrong");
            page = ConfigManager.getProperty("path.page.register");
        }

        return page;
    }
}
