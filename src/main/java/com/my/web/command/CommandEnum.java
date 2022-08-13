package com.my.web.command;

public enum CommandEnum {
    LOGIN{
        {
            this.command = new LoginCommand();
        }
    }, LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    }, REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCommand() {
        return command;
    }
}
