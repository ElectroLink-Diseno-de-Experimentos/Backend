package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoriesWithLowStockQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetInventoryByTechnicianIdQuery;
import com.hampcoders.electrolink.assets.domain.model.queries.GetStockItemDetailsQuery;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query handling service for the TechnicianInventory aggregate.
 */
public interface TechnicianInventoryQueryService {
  /**
   * Handles the query to retrieve an inventory by technician ID.
   *
   * @param query The query containing the technician ID.
   * @return An Optional containing the TechnicianInventory, or empty if not found.
   */
  Optional<TechnicianInventory> handle(GetInventoryByTechnicianIdQuery query);

  /**
   * Handles the query to retrieve inventories with stock below a given threshold.
   *
   * @param query The query containing the stock threshold.
   * @return A list of TechnicianInventory entities with low stock.
   */
  List<TechnicianInventory> handle(GetInventoriesWithLowStockQuery query);

  /**
   * Handles the query to retrieve details for a specific stock item.
   *
   * @param query The query containing technician and component IDs.
   * @return An Optional containing the ComponentStock details, or empty if not found.
   */
  Optional<ComponentStock> handle(GetStockItemDetailsQuery query);
}