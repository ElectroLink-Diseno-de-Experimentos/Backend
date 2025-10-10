package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.NotBlank;

/**
 * Resource used for updating basic component details.
 */
public record UpdateComponentResource(
    @NotBlank String name,
    String description,
    Long componentTypeId,
    Boolean isActive
) {}