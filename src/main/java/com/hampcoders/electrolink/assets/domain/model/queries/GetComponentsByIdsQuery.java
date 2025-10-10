package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import java.util.List;

/**
 * Query to retrieve a list of components based on a list of their unique IDs.
 */
public record GetComponentsByIdsQuery(List<ComponentId> ids) {
}