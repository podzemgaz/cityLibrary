package com.my.web.command;

import com.my.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {
    private static final Logger LOG = LogManager.getLogger(ActionFactory.class);
    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current = new EmptyCommand();
        //extract name of command from request
        String action = req.getParameter("command");
        LOG.trace("Command name in request: " + action);
        /*if (action == null) {
            action = (String) req.getSession().getAttribute("command");
            LOG.trace("Command name in session: " + action);

        } else {
            LOG.trace("Clear command in session");
            req.getSession().setAttribute("command", null);
        }*/
        if (action == null || action.isEmpty()) {
            return current;
        }

        //getting command that corresponds to name
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCommand();
        } catch (IllegalArgumentException e) {
            LOG.error("wrong action");
            req.getSession().setAttribute("errorMessage", action + MessageManager.getProperty("message.wrong_action"));
        }

        return current;
    }
}
