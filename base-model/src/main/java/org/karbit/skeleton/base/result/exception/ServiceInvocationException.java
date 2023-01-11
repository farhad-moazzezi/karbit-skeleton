package org.karbit.skeleton.base.result.exception;

public abstract class ServiceInvocationException extends BaseException {
	public ServiceInvocationException(String message) {
		super(message);
	}

	public ServiceInvocationException(Throwable cause) {
		super(cause);
	}

	public ServiceInvocationException(String message, Throwable cause) {
		super(message, cause);
	}
}
