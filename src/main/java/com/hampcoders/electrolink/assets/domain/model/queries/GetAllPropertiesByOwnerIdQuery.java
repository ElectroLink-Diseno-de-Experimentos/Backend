package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;

/**
 * Query to retrieve all properties owned by a specific owner ID.
 */
public record GetAllPropertiesByOwnerIdQuery(OwnerId ownerId) {
}