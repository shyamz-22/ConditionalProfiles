package io.github.shyamz.conditional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ConditionalOnAllProfilesConditionTest {

    private ConditionalOnAllProfilesCondition subject;

    @Before
    public void setUp() throws Exception {
        subject = new ConditionalOnAllProfilesCondition();
    }

    @Test
    public void throwsExceptionForEmptyProfileList() throws Exception {
        assertThatThrownBy(
                () -> subject.matches(mockConditionContext(), mockAnnotatedTypeMetaData()))
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("'value' attributes need more than one 'profile' values. For just one profile use '@Profile' annotation");
    }

    @Test
    public void throwsExceptionForOneProfile() throws Exception {
        assertThatThrownBy(
                () -> subject.matches(mockConditionContext(), mockAnnotatedTypeMetaData("test")))
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("'value' attributes need more than one 'profile' values. For just one profile use '@Profile' annotation");
    }

    @Test
    public void returnsTrueIfCurrentProfilesAreEqual() throws Exception {
        assertThat(subject.matches(mockConditionContext("test", "local"),
                mockAnnotatedTypeMetaData("test", "local"))).isTrue();
    }

    @Test
    public void returnsTrueIfCurrentProfilesAreEqualButInDifferentOrder() throws Exception {
        assertThat(subject.matches(mockConditionContext("test", "local"),
                mockAnnotatedTypeMetaData("local", "test"))).isTrue();
    }

    @Test
    public void returnsTrueIfCurrentProfilesAreSubsetOfActiveProfiles() throws Exception {
        assertThat(subject.matches(mockConditionContext("test", "local", "pact"),
                mockAnnotatedTypeMetaData("local", "test"))).isTrue();
    }

    @Test
    public void returnsFalseIfCurrentProfilesAreNotSubsetOfActiveProfiles() throws Exception {
        assertThat(subject.matches(mockConditionContext("test", "local"),
                mockAnnotatedTypeMetaData("local", "test", "pact"))).isFalse();
    }

    @Test
    public void returnsFalseIfCurrentProfilesAreDifferent() throws Exception {
        assertThat(subject.matches(mockConditionContext("test", "local"), mockAnnotatedTypeMetaData("cloud", "acceptance"))).isFalse();
    }

    private ConditionContext mockConditionContext(String... activeProfiles) {
        return new ConditionContext() {

            @Override
            public BeanDefinitionRegistry getRegistry() {
                return null;
            }

            @Override
            public ConfigurableListableBeanFactory getBeanFactory() {
                return null;
            }

            @Override
            public Environment getEnvironment() {
                MockEnvironment environment = new MockEnvironment();
                environment.withProperty("spring.profiles.active", String.join(",", activeProfiles));
                return environment;
            }

            @Override
            public ResourceLoader getResourceLoader() {
                return null;
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;
            }
        };

    }

    private AnnotatedTypeMetadata mockAnnotatedTypeMetaData(String... currentProfiles) {
        return new AnnotatedTypeMetadata() {
            @Override
            public boolean isAnnotated(String annotationName) {
                return true;
            }

            @Override
            public Map<String, Object> getAnnotationAttributes(String annotationName) {
                return Collections.singletonMap("value", currentProfiles);
            }

            @Override
            public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
                return null;
            }

            @Override
            public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName) {
                return null;
            }

            @Override
            public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
                return null;
            }
        };
    }

}