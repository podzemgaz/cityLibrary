package com.my.exception;

import java.io.Serial;

public class DaoException extends AppException{
    @Serial
    private static final long serialVersionUID = 5692454318940824319L;

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
