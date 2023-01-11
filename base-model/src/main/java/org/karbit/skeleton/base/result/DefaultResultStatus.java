package org.karbit.skeleton.base.result;

import java.util.HashSet;
import java.util.Set;

public class DefaultResultStatus extends AbstractResultStatus {
	public static final DefaultResultStatus SUCCESS = new DefaultResultStatus(200000000, "success");

	public static final DefaultResultStatus UNKNOWN_FAILURE = new DefaultResultStatus(422000000, "ex.unknown.failure");

	public static final DefaultResultStatus INVALID_INPUT_PARAM = new DefaultResultStatus(422000001, "ex.invalid.input.param");

	protected DefaultResultStatus(int statusCode, String bundleId) {
		super(statusCode, bundleId);
	}

	@Override
	public AbstractResultStatus getDefaultSuccessStatus() {
		return SUCCESS;
	}

	@Override
	public AbstractResultStatus getDefaultFailureStatus() {
		return UNKNOWN_FAILURE;
	}

	@Override
	protected Set<String> getBundleName() {
		return Set.of(DEFAULT_MESSAGE_PROPERTIES);
	}
}
