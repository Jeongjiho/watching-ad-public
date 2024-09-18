package com.watchingad.watchingad.exception.exception;

public final class AccessTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5892437532408602081L;

	public AccessTokenException() {
		super();
	}
	
    public AccessTokenException(String message) {
        super(message);
    }

    public AccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenException(Throwable cause) {
        super(cause);
    }

    protected AccessTokenException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 

}
