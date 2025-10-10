package com.hampcoders.electrolink.assets.application.internal.queryservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.TechnicianInventoryQueryService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentStockRepository;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.TechnicianInventoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Implementation of the query service for the TechnicianInventory aggregate.
 */
@Service
public class TechnicianInventoryQueryServiceImpl implements TechnicianInventoryQueryService {

  private final TechnicianInventoryRepository technicianInventoryRepository;
  private final ComponentStockRepository componentStockRepository;

  /**
   * Constructs a TechnicianInventoryQueryServiceImpl.
   *
   * @param technicianInventoryRepository Repository for technician inventories.
   * @param componentStockRepository Repository for component stocks.
   */
  public TechnicianInventoryQueryServiceImpl(
      final TechnicianInventoryRepository technicianInventoryRepository,
      final ComponentStockRepository componentStockRepository) {
    this.technicianInventoryRepository = technicianInventoryRepository;
    this.componentStockRepository = componentStockRepository;
  }

  @Override
  public Optional<TechnicianInventory> handle(final GetInventoryByTechnicianIdQuery query) {
    return technicianInventoryRepository.findByTechnicianIdWithStocks(
        query.technicianId().technicianId());
  }

  @Override
  public List<TechnicianInventory> handle(final GetInventoriesWithLowStockQuery query) {
    return technicianInventoryRepository.findInventoriesWithLowStock(query.threshold());
  }

  @Override
  public Optional<ComponentStock> handle(final GetStockItemDetailsQuery query) {
    Long componentId = query.componentId().componentId();

    return componentStockRepository.findByTechnicianInventoryIdAndComponentUid(
        query.technicianId().technicianId(), componentId);
  }
}