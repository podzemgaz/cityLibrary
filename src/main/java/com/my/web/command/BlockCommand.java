package com.my.web.command;

import com.my.logic.UserLogic;
import com.my.util.ConfigManager;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BlockCommand implements ActionCommand {
    private static final Logger LOG = LogManager.getLogger(BlockCommand.class);
    @Override
    public String execute(HttpServletRequest req) {

        String strId = req.getParameter("user_id");
        LOG.trace("userId in request: " + strId);

        String page = ConfigManager.getProperty("path.mapping.users");

        if (strId != null) {
            int id = Integer.parseInt(req.getParameter("user_id"));
            if (!UserLogic.block(id)) {
                req.setAttribute("errorMessage", "Cant block");
            } else {
                LOG.trace("Successful");
            }
        }

        return page;
    }
}
