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
    }, USERS {
        {
            this.command = new UsersCommand();
        }
    }, BLOCK {
        {
            this.command = new BlockCommand();
        }
    }, MODER {
        {
            this.command = new ModerCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCommand() {
        return command;
    }
}
