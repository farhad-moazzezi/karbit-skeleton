package org.karbit.post.biz.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthorProfile {
	private String userId;

	private String nickname;
}
