package org.karbit.skeleton.base.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class AbstractResultStatus {

	protected final static String DEFAULT_MESSAGE_PROPERTIES = "default-message";

	protected int statusCode;

	protected String bundleId;

	private final Properties properties = loadBundle();

	public abstract AbstractResultStatus getDefaultSuccessStatus();

	public abstract AbstractResultStatus getDefaultFailureStatus();

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return findMessage(this.bundleId);
	}

	protected abstract Set<String> getBundleName();

	protected AbstractResultStatus(int statusCode, String bundleId) {
		this.statusCode = statusCode;
		this.bundleId = bundleId;
	}

	protected String findMessage(String bundleId) {
		return properties.getProperty(bundleId);
	}

	protected Properties loadBundle() {
		Properties properties = new Properties();
		Set<String> bundles = new HashSet<>();
		bundles.add(DEFAULT_MESSAGE_PROPERTIES);
		bundles.addAll(getBundleName());
		bundles.forEach(bundleName -> {
			try {
				ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName, Locale.getDefault(), this.getClass().getClassLoader());
				Enumeration<String> keys = resourceBundle.getKeys();
				ArrayList<String> keysList = Collections.list(keys);
				keysList.forEach(key -> properties.put(key, resourceBundle.getString(key)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return properties;
	}
}
