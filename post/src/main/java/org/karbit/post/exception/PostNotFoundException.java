package org.karbit.post.exception;

import org.karbit.post.DefaultPostResultStatus;
import org.karbit.skeleton.base.result.AbstractResultStatus;
import org.karbit.skeleton.base.result.exception.BaseException;

public class PostNotFoundException extends BaseException {
	public PostNotFoundException(String message) {
		super(message);
	}

	@Override
	public AbstractResultStatus getResult() {
		return DefaultPostResultStatus.EX_POST_NOT_FOUND;
	}
}
