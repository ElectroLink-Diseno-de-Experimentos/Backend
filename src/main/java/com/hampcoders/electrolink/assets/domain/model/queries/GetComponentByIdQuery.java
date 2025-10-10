package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;

/**
 * Query to retrieve a specific component by its unique ID.
 */
public record GetComponentByIdQuery(ComponentId componentId) {
}