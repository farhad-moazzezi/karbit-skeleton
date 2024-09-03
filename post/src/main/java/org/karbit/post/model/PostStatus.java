package org.karbit.post.model;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.util.CollectionUtils;

public enum PostStatus implements PostableStatus {
	REMOVED(0),
	SUSPENDED(1),
	CONFIRMED(2),
	PEND(3),
	DRAFTED(4),
	REJECTED(5),
	CHECKING(6);

	private final int id;

	PostStatus(int id) {
		this.id = id;
	}

	@Override
	public void validateNextState(PostStatus targetState) {
		Iterator<PostStatus> validNextStates = Arrays.stream(getNextState(this)).iterator();
		if (Boolean.FALSE.equals(CollectionUtils.contains(validNextStates, targetState))) {
			throw new IllegalStateException("Can not change state of Post to -> " + targetState);
		}
	}

	@Override
	public PostStatus[] getNextState(PostStatus status) {
		return switch (status) {
			case REMOVED -> null;
			case CONFIRMED -> new PostStatus[] { SUSPENDED, REMOVED };
			case PEND -> new PostStatus[] { DRAFTED, REMOVED };
			case DRAFTED -> new PostStatus[] { PEND, REMOVED };
			case REJECTED -> new PostStatus[] { DRAFTED };
			case CHECKING -> new PostStatus[] { CONFIRMED, REJECTED };
			default -> throw new IllegalStateException("Unexpected value: " + status);
		};
	}

	@Override
	public int getId() {
		return id;
	}

	public static PostStatus of(Integer statusId) {
		return Arrays.stream(PostStatus.values()).filter(status -> status.id == statusId).findFirst().orElseThrow(() -> new IllegalArgumentException("ca not find post status with id: " + statusId));
	}
}
