package org.karbit.post.biz;

import org.karbit.post.model.Post;
import org.karbit.post.model.PostStatus;

public interface PostChangeStateManager<P extends Post> extends PostFinder<P> {

	default void changeState(String postId, PostStatus targetState) {
		getLogger().info("going to change state of post -> postId : {} , target state : {}", postId, targetState);
		P post = findById(postId);
		post.getStatus().validateNextState(targetState);
		post.setStatus(targetState);
		getDao().save(post);
	}
}
