package org.karbit.test.configurer;

import java.util.Map;

import org.karbit.test.Container;

public interface ContainerConfigurer {

	void start();

	void stop();

	Container getType();

	Map<String, String> getProperties();
}
