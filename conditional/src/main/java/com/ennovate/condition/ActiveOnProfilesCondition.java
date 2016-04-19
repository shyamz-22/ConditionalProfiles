package com.ennovate.condition;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.Map;


public class ActiveOnProfilesCondition implements Condition {

    @Autowired
    Environment environment;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes
                = metadata.getAnnotationAttributes(ConditionalOnProfiles.class.getName());
        String[] profilesToBeActive = (String[]) attributes.get("value");
        final String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        return (Arrays.equals(activeProfiles, profilesToBeActive));
    }
}
