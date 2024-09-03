package org.karbit.post.biz;

import org.karbit.post.exception.PostNotFoundException;
import org.karbit.post.model.Post;
import org.karbit.post.model.PostStatus;
import org.karbit.skeleton.base.result.exception.BaseException;

public interface PostChangeStateManager<P extends Post> extends PostManager<P> {

	default void changeState(String postId, PostStatus targetState) throws BaseException {
		getLogger().info("going to change state of post -> postId : {} , target state : {}", postId, targetState);
		P post = getDao().findByUniqueId(postId).orElseThrow(() -> new PostNotFoundException("could not found post by id -> postId : " + postId));
		post.getStatus().validateNextState(targetState);
		post.setStatus(targetState);
		getDao().save(post);
	}
}
