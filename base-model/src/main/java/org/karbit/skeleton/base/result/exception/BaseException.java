package org.karbit.skeleton.base.result.exception;

import org.karbit.skeleton.base.result.AbstractResultStatus;

public abstract class BaseException extends RuntimeException {

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract AbstractResultStatus getResult();
}
