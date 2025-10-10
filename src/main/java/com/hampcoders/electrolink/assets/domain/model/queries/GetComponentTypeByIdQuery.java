package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

/**
 * Query to retrieve a specific component type by its unique ID.
 */
public record GetComponentTypeByIdQuery(ComponentTypeId componentTypeId) {
}