package org.karbit.skeleton.base.result.dto;

import java.util.Objects;

import lombok.Data;
import org.karbit.skeleton.base.result.AbstractResultStatus;

@Data
public class ResultSummary {
	private long status;

	private String message;

	public ResultSummary() {
	}

	private ResultSummary(long status, String message) {
		this.status = status;
		this.message = message;
	}

	private ResultSummary(AbstractResultStatus abstractResultStatus) {
		this.status = abstractResultStatus.getStatusCode();
		this.message = abstractResultStatus.getMessage();
	}

	public static ResultSummary of(AbstractResultStatus abstractResultStatus) {
		return new ResultSummary(abstractResultStatus);
	}

	public static ResultSummary of(long status, String message) {
		return new ResultSummary(status, message);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResultSummary that = (ResultSummary) o;
		return status == that.status && message.equals(that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, message);
	}
}
