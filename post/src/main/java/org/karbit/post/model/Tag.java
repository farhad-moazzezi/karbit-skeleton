package org.karbit.post.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

	@Exclude
	private String categoryId;

	private String tagId;

	@Exclude
	private String caption;
}
