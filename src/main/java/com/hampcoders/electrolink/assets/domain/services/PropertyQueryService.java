package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPhotosByPropertyIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.PropertyPhoto;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query handling service for the Property aggregate.
 */
public interface PropertyQueryService {
  /**
   * Handles the query to retrieve a property by its UUID.
   *
   * @param query The query containing the property ID.
   * @return An Optional containing the property, or empty if not found.
   */
  Optional<Property> handle(GetPropertyByIdQuery query);

  /**
   * Handles the query to retrieve all properties belonging to a specific owner.
   *
   * @param query The query containing the owner ID.
   * @return A list of properties belonging to the owner.
   */
  List<Property> handle(GetAllPropertiesByOwnerIdQuery query);

  /**
   * Handles the query to retrieve all properties in the system.
   *
   * @param query The query.
   * @return A list of all properties.
   */
  List<Property> handle(GetAllPropertiesQuery query);
}