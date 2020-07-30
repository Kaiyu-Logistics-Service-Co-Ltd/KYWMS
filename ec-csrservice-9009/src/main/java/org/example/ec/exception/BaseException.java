package org.example.ec.exception;

/**
 * @auther cssly
 * @create 2020/6/30 21:47
 */
public abstract class BaseException extends RuntimeException{
    private String message;

    public BaseException(String msg)
    {
        this.message = msg;
    }
    public String getMessage() {
        return message;
    }
}