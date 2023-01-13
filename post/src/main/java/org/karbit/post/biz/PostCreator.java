package org.karbit.post.biz;

import java.util.Set;

import org.karbit.post.biz.dto.AuthorProfile;
import org.karbit.post.model.Author;
import org.karbit.post.model.Post;
import org.karbit.post.model.Tag;

public interface PostCreator<P extends Post> extends PostManager<P> {

	Set<Tag> findOrInsertTag(Set<String> tagLabel);

	default P create(P post, Set<String> tagCaptions) {
		getLogger().info("going to create post -> {}", post);
		checkBeforeCreate(post);
		fillTag(post, tagCaptions);
		fillAuthor(post);
		post.setLastModifyDate(post.getCreationDate());
		doBeforeSave(post);
		post = save(post);
		doAfterSave(post);
		return post;
	}

	default void fillTag(P post, Set<String> tagCaptions) {
		getLogger().debug("going to fill tags of the post -> {}", post);
		Set<Tag> tags = findOrInsertTag(tagCaptions);
		checkAfterFoundTags(tags);
		post.setTags(tags);
	}

	default void fillAuthor(P post) {
		getLogger().debug("going to fill author of the post -> {}", post);
		AuthorProfile profile = getAuthorProfile(post.getAuthor().getUserId());
		post.setAuthor(
				Author.builder().userId(profile.getUserId()).nickname(profile.getNickname()).build()
		);
	}

	default void checkAfterFoundTags(Set<Tag> tags) {
	}

	default void checkBeforeCreate(P post) {
		getLogger().debug("going to check input value -> {}", post);
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
