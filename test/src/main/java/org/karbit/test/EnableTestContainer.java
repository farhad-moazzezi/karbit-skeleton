package org.karbit.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.karbit.test.Container;
import org.karbit.test.TestContainer;

import org.springframework.test.context.TestExecutionListeners;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@TestExecutionListeners(value = TestContainer.class,
		mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface EnableTestContainer {
	Container[] containers();
}
