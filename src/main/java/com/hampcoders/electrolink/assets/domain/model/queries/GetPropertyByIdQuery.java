package com.hampcoders.electrolink.assets.domain.model.queries;

import java.util.UUID;

/**
 * Query to retrieve a specific property by its unique ID.
 */
public record GetPropertyByIdQuery(UUID propertyId) {
}