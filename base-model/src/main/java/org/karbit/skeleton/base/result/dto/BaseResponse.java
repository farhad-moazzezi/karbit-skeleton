package org.karbit.skeleton.base.result.dto;

import lombok.Data;

@Data
public abstract class BaseResponse {
	private ResultSummary result;

	public BaseResponse() {
	}
}
