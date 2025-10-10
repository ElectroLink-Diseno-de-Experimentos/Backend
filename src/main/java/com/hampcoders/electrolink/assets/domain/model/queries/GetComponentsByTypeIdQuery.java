package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

/**
 * Query to retrieve components associated with a specific component type ID.
 */
public record GetComponentsByTypeIdQuery(Long componentTypeId) {
}