package org.karbit.skeleton.base.result.exception;

public class NotImplementedException extends RuntimeException {
	public NotImplementedException(String message) {
		super(message);
	}

	public NotImplementedException(Throwable cause) {
		super(cause);
	}

	public NotImplementedException(String message, Throwable cause) {
		super(message, cause);
	}
}
