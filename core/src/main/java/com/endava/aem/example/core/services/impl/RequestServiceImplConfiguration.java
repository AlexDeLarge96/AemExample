package com.endava.aem.example.core.services.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Request Service",
        description = "Request Service API Configuration"
)
public @interface RequestServiceImplConfiguration {

    @AttributeDefinition(name = "hostname",description = "API Hostname",type = AttributeType.STRING)
    String hostname();

    @AttributeDefinition(name = "port",description = "API Port",type = AttributeType.STRING)
    String port();
}
