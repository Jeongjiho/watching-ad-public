package com.watchingad.watchingad.exception.exception;

public final class NotFoundTokenException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6352122575608980644L;

	public NotFoundTokenException() {
		super();
	}
	
    public NotFoundTokenException(String message) {
        super(message);
    }

    public NotFoundTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundTokenException(Throwable cause) {
        super(cause);
    }

    protected NotFoundTokenException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
