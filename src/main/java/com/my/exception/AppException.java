package com.my.exception;

import java.io.Serial;

public class AppException extends Exception{
    @Serial
    private static final long serialVersionUID = -4865816972454956300L;


    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
