package org.karbit.post.biz;

import java.util.List;
import java.util.Set;

import org.karbit.post.model.Post;
import org.karbit.post.model.PostableStatus;

import org.springframework.data.domain.PageRequest;

public interface PostFinder<P extends Post> extends PostManager<P> {

	default List findByTitle(String value, Set<PostableStatus> status, Integer pageNumber, Integer pageSize) {
		return getDao().findByTitleLikeAndStatusIn(value, status, PageRequest.of(pageNumber, pageSize));
	}

	default List findByAuthor(String userId, Set<PostableStatus> status, Integer pageNumber, Integer pageSize) {
		return getDao().findByAuthorIdAndStatusIn(userId, status, PageRequest.of(pageNumber, pageSize));
	}

	default List find(Set<PostableStatus> status, Integer pageNumber, Integer pageSize) {
		return getDao().findByStatusIn(status, PageRequest.of(pageNumber, pageSize));
	}

	default P findById(String postId) {
		P post = getDao().findByUniqueId(postId);
		getLogger().info("an post found : {}", post);
		return post;
	}
}
