package com.watchingad.watchingad.exception.exception;

public final class UserAlreadyJoinException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2700889747781691772L;
	
	public UserAlreadyJoinException() {
		super();
	}
	
    public UserAlreadyJoinException(String message) {
        super(message);
    }

    public UserAlreadyJoinException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyJoinException(Throwable cause) {
        super(cause);
    }

    protected UserAlreadyJoinException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 

}
