package org.karbit.skeleton.base.result.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchableReq extends BaseRequest {
	private String value;

	private int page = 0;
}
