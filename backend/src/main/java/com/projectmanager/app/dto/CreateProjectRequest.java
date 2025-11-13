package com.projectmanager.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectRequest {
    @NotBlank(message = "Project name is required")
    private String name;

    @NotBlank(message = "Project key is required")
    private String projectKey;

    private String description;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;
}
