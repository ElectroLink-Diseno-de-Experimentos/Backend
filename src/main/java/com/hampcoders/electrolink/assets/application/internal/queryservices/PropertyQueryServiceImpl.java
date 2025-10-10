package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPhotosByPropertyIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesByOwnerIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllPropertiesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetPropertyByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.PropertyPhoto;
import com.hampcoders.electrolink.assets.domain.services.PropertyQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.PropertyRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Implementation of the query service for the Property aggregate.
 */
@Service
public class PropertyQueryServiceImpl implements PropertyQueryService {

  private final PropertyRepository propertyRepository;

  /**
   * Constructs a PropertyQueryServiceImpl.
   *
   * @param propertyRepository The repository for Property entities.
   */
  public PropertyQueryServiceImpl(final PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public Optional<Property> handle(final GetPropertyByIdQuery query) {
    return propertyRepository.findById(UUID.fromString(query.propertyId().toString()));
  }

  @Override
  public List<Property> handle(final GetAllPropertiesByOwnerIdQuery query) {
    return propertyRepository.findPropertiesByOwnerId(query.ownerId());
  }

  @Override
  public List<Property> handle(final GetAllPropertiesQuery query) {
    return propertyRepository.findAll();
  }
}