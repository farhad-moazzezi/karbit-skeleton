package org.karbit.post.model;

public interface PostableStatus {
	void validateNextState(PostStatus targetState);

	PostStatus[] getNextState(PostStatus status);

	int getId();
}
