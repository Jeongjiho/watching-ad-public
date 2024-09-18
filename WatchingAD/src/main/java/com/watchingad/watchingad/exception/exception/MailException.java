package com.watchingad.watchingad.exception.exception;

public final class MailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2700889747781691772L;
	
	public MailException() {
		super();
	}
	
    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailException(Throwable cause) {
        super(cause);
    }

    protected MailException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 

}
