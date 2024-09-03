package org.karbit.skeleton.base.result;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class AbstractResultStatus {

	protected final static String DEFAULT_MESSAGE_PROPERTIES = "default-message";

	private final MessageSource messageSource = getMessageSource();

	protected int statusCode;

	protected String bundleId;

	public abstract AbstractResultStatus getDefaultSuccessStatus();

	public abstract AbstractResultStatus getDefaultFailureStatus();

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return getMessage(bundleId);
	}

	protected MessageSource getMessageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		resourceBundleMessageSource.setBasenames(getMessageResourceName());
		return resourceBundleMessageSource;
	}

	protected abstract String[] getMessageResourceName();

	protected AbstractResultStatus(int statusCode, String bundleId) {
		this.statusCode = statusCode;
		this.bundleId = bundleId;
	}

	public String getMessage(String messageId, Object... messageParameter) {
		return messageSource.getMessage(messageId, messageParameter, Locale.getDefault());
	}
}
