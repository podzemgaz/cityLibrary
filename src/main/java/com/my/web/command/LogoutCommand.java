package com.my.web.command;

import com.my.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigManager.getProperty("path.page.main");

        req.getSession().invalidate();

        return page;
    }
}
