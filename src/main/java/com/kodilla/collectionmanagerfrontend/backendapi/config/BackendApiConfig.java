package com.kodilla.collectionmanagerfrontend.backendapi.config;

import lombok.Getter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BackendApiConfig {
    @Value("${backend.api.endpoint.prod}")
    private String backendApiEndpoints;
}
