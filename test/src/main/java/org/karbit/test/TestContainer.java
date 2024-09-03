package org.karbit.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.karbit.test.configurer.ContainerConfigurer;
import org.karbit.test.configurer.ContainerConfigurerContext;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class TestContainer implements TestExecutionListener {

	private final static Map<String, String> properties = new HashMap<>();

	private final static ContainerConfigurerContext containerConfigurerContext = new ContainerConfigurerContext();

	private List<ContainerConfigurer> containerConfigurers;

	@Override
	public void beforeTestClass(TestContext testContext) throws ExecutionException, InterruptedException {
		EnableTestContainer enableTestContainer = AnnotationUtils.findAnnotation(testContext.getTestClass(), EnableTestContainer.class);
		if (Objects.isNull(enableTestContainer)) {
			return;
		}
		Container[] enabledContainers = enableTestContainer.containers();
		containerConfigurers = containerConfigurerContext.get(Arrays.stream(enabledContainers).toList());
		runAllEnabledContainers();
		describeContainerPropertiesToSpringContext();
	}

	private void runAllEnabledContainers() throws InterruptedException, ExecutionException {
		var containerRunner = containerConfigurers.stream().map(containerConfigurer -> CompletableFuture.runAsync(containerConfigurer::start)).toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(containerRunner).get();
	}

	private void describeContainerPropertiesToSpringContext() {
		containerConfigurers.forEach(configurer -> properties.putAll(configurer.getProperties()));
		properties.forEach(System::setProperty);
	}
}

