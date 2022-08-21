package com.my.web.command;

import com.my.logic.UserLogic;
import com.my.util.ConfigManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModerCommand implements ActionCommand{
    private static final Logger LOG = LogManager.getLogger(ModerCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        String strId = req.getParameter("user_id");
        LOG.trace("userId in request: " + strId);

        String page = ConfigManager.getProperty("path.page.users");

        if (strId != null) {
            int id = Integer.parseInt(req.getParameter("user_id"));
            if (!UserLogic.moder(id)) {
                req.setAttribute("errorMessage", "Cant moder/unmoder");
            } else {
                LOG.trace("Successful");
            }
        }

        return page;
    }
}
