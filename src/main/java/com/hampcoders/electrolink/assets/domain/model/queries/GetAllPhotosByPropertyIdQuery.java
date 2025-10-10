package com.hampcoders.electrolink.assets.domain.model.queries;

import java.util.UUID;

/**
 * Query to retrieve all photos associated with a specific property ID.
 */
public record GetAllPhotosByPropertyIdQuery(UUID propertyId) {
}