package com.yzcx.ucar.common.exception;

/**
 * 业务异常
 * 
 * @author maozhr
 */
public class ConfException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public ConfException(String message)
    {
        this.message = message;
    }

    public ConfException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
