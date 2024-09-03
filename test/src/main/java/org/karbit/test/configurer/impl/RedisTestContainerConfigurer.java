package org.karbit.test.configurer.impl;

import java.util.HashMap;
import java.util.Map;

import org.karbit.test.Container;
import org.karbit.test.configurer.ContainerConfigurer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisTestContainerConfigurer implements ContainerConfigurer {

	private final static Integer EXPOSE_PORT = 6379;

	public static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:6-alpine"))
			.withExposedPorts(EXPOSE_PORT);

	@Override
	public void start() {
		redis.start();
	}

	@Override
	public void stop() {
		redis.stop();
	}

	@Override
	public Container getType() {
		return Container.REDIS;
	}

	@Override
	public Map<String, String> getProperties() {
		var properties = new HashMap<String, String>();

		properties.put("spring.redis.host", redis.getHost());
		properties.put("spring.redis.port", String.valueOf(redis.getFirstMappedPort()));

		return properties;
	}
}
