package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.queries.GetAllComponentsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentByIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByIdsQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByNameQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetComponentsByTypeIdQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Implementation of the query service for the Component aggregate.
 */
@Service
public class ComponentQueryServiceImpl implements ComponentQueryService {

  private final ComponentRepository componentRepository;

  /**
   * Constructs a ComponentQueryServiceImpl.
   *
   * @param componentRepository The repository for Component entities.
   */
  public ComponentQueryServiceImpl(final ComponentRepository componentRepository) {
    this.componentRepository = componentRepository;
  }

  @Override
  public Optional<Component> handle(final GetComponentByIdQuery query) {
    return componentRepository.findById(query.componentId().componentId());
  }

  @Override
  public List<Component> handle(final GetAllComponentsQuery query) {
    return componentRepository.findAll();
  }

  @Override
  public List<Component> handle(final GetComponentsByTypeIdQuery query) {
    return componentRepository.findByComponentTypeId(query.componentTypeId());
  }

  @Override
  public List<Component> handle(final GetComponentsByIdsQuery query) {
    List<Long> uuidList = query.ids().stream()
        .map(ComponentId::componentId)
        .toList();
    return componentRepository.findByComponentUidIn(uuidList);
  }

  @Override
  public List<Component> handle(final GetComponentsByNameQuery query) {
    return componentRepository
        .findTop10ByNameContainingIgnoreCase(query.term())
        .stream()
        .limit(query.limit())
        .toList();
  }
}