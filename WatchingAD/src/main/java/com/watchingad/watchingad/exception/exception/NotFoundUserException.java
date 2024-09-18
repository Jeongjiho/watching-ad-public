package com.watchingad.watchingad.exception.exception;

public final class NotFoundUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4144906620358062889L;

	public NotFoundUserException() {
		super();
	}
	
    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUserException(Throwable cause) {
        super(cause);
    }

    protected NotFoundUserException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 

}
