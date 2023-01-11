package org.karbit.post.biz;

import java.util.Set;

import org.karbit.post.model.Post;
import org.karbit.post.model.Tag;

public interface PostCreator<P extends Post> extends PostManager<P>, PostFinder<P> {

	Set<Tag> findOrInsertTag(Set<String> tagLabel);

	default P create(P post, Set<String> tagCaptions) {
		getLogger().info("going to create post -> {}", post);
		checkBeforeCreate(post);
		Set<Tag> tags = findOrInsertTag(tagCaptions);
		checkAfterFoundTags(tags);
		post.setTags(tags);
		post.setAuthorNickName(
				getAuthorProfile(post.getAuthorId()).getNickname()
		);
		doBeforeSave(post);
		post = save(post);
		doAfterSave(post);
		return post;
	}

	default void checkAfterFoundTags(Set<Tag> tags) {
	}

	default void checkBeforeCreate(P post) {
		getLogger().info("going to check input value -> {}", post);
		defaultCheckingBeforeCreate(post);
		moreCheckingBeforeCreate(post);
	}

	default P save(P post) {
		getLogger().debug("going to save post -> {}", post);
		return getDao().save(post);
	}

	default void defaultCheckingBeforeCreate(P post) {
	}

	default void moreCheckingBeforeCreate(P post) {
	}

	default void doBeforeSave(P post) {
	}

	default void doAfterSave(P post) {
	}
}
