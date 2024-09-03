package org.karbit.test.configurer;

import java.util.ArrayList;
import java.util.List;

import org.karbit.test.Container;
import org.karbit.test.configurer.impl.MongoTestContainerConfigurer;
import org.karbit.test.configurer.impl.RedisTestContainerConfigurer;

public class ContainerConfigurerContext {

	private final List<ContainerConfigurer> containerConfigurers;

	public ContainerConfigurerContext() {
		containerConfigurers = new ArrayList<>();
		containerConfigurers.add(new MongoTestContainerConfigurer());
		containerConfigurers.add(new RedisTestContainerConfigurer());
	}

	public final List<ContainerConfigurer> get(List<Container> containers) {
		return containerConfigurers.stream().filter(configurer -> containers.contains(configurer.getType())).toList();
	}
}
