package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query handling service for the ComponentType aggregate.
 */
public interface ComponentTypeQueryService {
  /**
   * Handles the query to retrieve a component type by its ID.
   *
   * @param query The query containing the component type ID.
   * @return An Optional containing the ComponentType, or empty if not found.
   */
  Optional<ComponentType> handle(GetComponentTypeByIdQuery query);

  /**
   * Handles the query to retrieve all component types.
   *
   * @param query The query.
   * @return A list of all ComponentType entities.
   */
  List<ComponentType> handle(GetAllComponentTypesQuery query);
}