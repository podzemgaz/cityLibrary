package com.my.web.command;

import com.my.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest req) {
        /*if error or straight call to controller
        * redirect to login page*/
        return ConfigManager.getProperty("path.page.index");
    }
}
