package org.karbit.post;

import org.karbit.skeleton.base.result.AbstractResultStatus;
import org.karbit.skeleton.base.result.DefaultResultStatus;

public class DefaultPostResultStatus extends DefaultResultStatus {

	public static final DefaultPostResultStatus EX_POST_NOT_FOUND = new DefaultPostResultStatus(422001001, "ex.not.found.post");

	protected DefaultPostResultStatus(int statusCode, String bundleId) {
		super(statusCode, bundleId);
	}

	@Override
	public AbstractResultStatus getDefaultSuccessStatus() {
		return DefaultResultStatus.SUCCESS;
	}

	@Override
	public AbstractResultStatus getDefaultFailureStatus() {
		return DefaultResultStatus.UNKNOWN_FAILURE;
	}
}
