package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByIdsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByNameQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByTypeIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query handling service for the Component aggregate.
 */
public interface ComponentQueryService {
  /**
   * Handles the query to retrieve a component by its ID.
   *
   * @param query The query containing the component ID.
   * @return An Optional containing the component, or empty if not found.
   */
  Optional<Component> handle(GetComponentByIdQuery query);

  /**
   * Handles the query to retrieve all components.
   *
   * @param query The query.
   * @return A list of all components.
   */
  List<Component> handle(GetAllComponentsQuery query);

  /**
   * Handles the query to retrieve components by their type ID.
   *
   * @param query The query containing the component type ID.
   * @return A list of components matching the type ID.
   */
  List<Component> handle(GetComponentsByTypeIdQuery query);

  /**
   * Handles the query to retrieve components by a list of their IDs.
   *
   * @param query The query containing the list of component IDs.
   * @return A list of components matching the provided IDs.
   */
  List<Component> handle(GetComponentsByIdsQuery query);

  /**
   * Handles the query to retrieve components by name search term.
   *
   * @param query The query containing the search term and limit.
   * @return A list of components matching the search term.
   */
  List<Component> handle(GetComponentsByNameQuery query);
}