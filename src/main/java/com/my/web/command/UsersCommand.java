package com.my.web.command;

import com.my.entity.User;
import com.my.logic.UserLogic;
import com.my.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersCommand implements ActionCommand{
    private static final Logger LOG = LogManager.getLogger(UsersCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        LOG.trace("command starts");
        List<User> users = UserLogic.getUsers();
        req.setAttribute("users", users);

        return ConfigManager.getProperty("path.page.users");
    }
}
