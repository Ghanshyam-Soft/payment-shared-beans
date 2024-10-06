package com.staples.payment.shared.exceptions;

public class CustomException extends RuntimeException
{
	private static final long serialVersionUID = -214811676751710780L;

	public CustomException(String message)
	{
		super(message);
	}

	public CustomException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
