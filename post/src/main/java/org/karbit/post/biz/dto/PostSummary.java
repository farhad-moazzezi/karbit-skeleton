package org.karbit.post.biz.dto;

import java.util.Map;

import lombok.Data;

@Data
public class PostSummary {
	private String postId;

	private String title;

	private String summary;

	private AuthorProfile author;

	private Map<String, String> tags;

	private long modifyDate;
}
