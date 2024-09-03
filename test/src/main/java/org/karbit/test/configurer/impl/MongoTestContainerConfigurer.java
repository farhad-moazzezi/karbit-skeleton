package org.karbit.test.configurer.impl;

import java.util.HashMap;
import java.util.Map;

import org.karbit.test.Container;
import org.karbit.test.configurer.ContainerConfigurer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoTestContainerConfigurer implements ContainerConfigurer {

	private final static Integer EXPOSE_PORT = 27017;

	public static MongoDBContainer mongodb = new MongoDBContainer(DockerImageName.parse("mongo")).withExposedPorts(EXPOSE_PORT);

	@Override
	public void start() {
		mongodb.start();
	}

	@Override
	public void stop() {
		mongodb.stop();
	}

	@Override
	public Container getType() {
		return Container.MONGO_DB;
	}

	@Override
	public Map<String, String> getProperties() {
		var properties = new HashMap<String, String>();

		properties.put("spring.data.mongodb.host", mongodb.getHost());
		properties.put("spring.data.mongodb.port", String.valueOf(mongodb.getFirstMappedPort()));

		return properties;
	}
}
