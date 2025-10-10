package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentTypesQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentTypeByIdQuery;
import com.hampcoders.electrolink.assets.domain.services.ComponentTypeQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentTypeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of the query service for the ComponentType aggregate.
 */
@Service
public class ComponentTypeQueryServiceImpl implements ComponentTypeQueryService {

  private final ComponentTypeRepository componentTypeRepository;

  /**
   * Constructs a ComponentTypeQueryServiceImpl.
   *
   * @param componentTypeRepository The repository for ComponentType entities.
   */
  public ComponentTypeQueryServiceImpl(final ComponentTypeRepository componentTypeRepository) {
    this.componentTypeRepository = componentTypeRepository;
  }

  @Override
  public Optional<ComponentType> handle(final GetComponentTypeByIdQuery query) {
    return componentTypeRepository.findById(query.componentTypeId().id());
  }

  @Override
  public List<ComponentType> handle(final GetAllComponentTypesQuery query) {
    return componentTypeRepository.findAll();
  }
}