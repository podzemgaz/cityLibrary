package com.my.web.command;

import jakarta.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest req);
}
