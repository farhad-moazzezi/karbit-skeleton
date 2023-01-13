package org.karbit.post.model;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.karbit.skeleton.mongo.model.BaseMongoEntity;

import org.springframework.data.mongodb.core.index.Indexed;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Post extends BaseMongoEntity {

	@Exclude
	@Indexed
	private String title;

	@Exclude
	private String content;

	private Author author;

	@Indexed
	private PostStatus status;

	private Long lastModifyDate;

	private Set<Tag> tags;
}
