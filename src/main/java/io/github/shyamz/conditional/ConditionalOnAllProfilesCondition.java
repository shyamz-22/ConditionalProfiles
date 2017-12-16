package io.github.shyamz.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class ConditionalOnAllProfilesCondition implements Condition {

    private List<String> currentProfiles = new ArrayList<>();
    private List<String> activeProfiles = new ArrayList<>();

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata
                .getAnnotationAttributes(ConditionalOnAllProfiles.class.getName());

        populateCurrentProfiles((String[]) attributes.get("value"));
        populateActiveProfiles(context.getEnvironment().getActiveProfiles());

        return currentProfiles.stream().allMatch(activeProfiles::contains);
    }

    private void populateCurrentProfiles(String[] attributeValues) {
        currentProfiles.clear();
        currentProfiles.addAll(Arrays.asList(attributeValues));
        validateCurrentProfiles();
    }

    private void validateCurrentProfiles() {
        if (currentProfiles.isEmpty() || currentProfiles.size() == 1) {
            throw new RuntimeException("'value' attributes need more than one 'profile' values. For just one profile use '@Profile' annotation");
        }
    }

    private void populateActiveProfiles(String[] activeProfileValues) {
        activeProfiles.clear();
        activeProfiles.addAll(Arrays.asList(activeProfileValues));
    }
}
