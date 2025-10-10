package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.commands.AddComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
// import com.hampcoders.electrolink.assets.domain.model.commands.AddStockToInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentStockCommand;
// import com.hampcoders.electrolink.assets.domain.model.commands.IncreaseStockCommand;
// import com.hampcoders.electrolink.assets.domain.model.commands.DecreaseStockCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
// import com.hampcoders.electrolink.assets.domain.model.commands.RemoveComponentStockCommand;
import java.util.Optional;
import java.util.UUID;

/**
 * Defines the command handling service for the TechnicianInventory aggregate.
 */
public interface TechnicianInventoryCommandService {
  /**
   * Handles the creation of a new technician inventory.
   *
   * @param command The command to create the inventory.
   * @return The UUID of the newly created inventory.
   */
  UUID handle(CreateTechnicianInventoryCommand command);

  /**
   * Handles adding a component stock item to an existing inventory.
   *
   * @param command The command to add stock.
   * @return An Optional containing the updated TechnicianInventory, or empty if not found.
   */
  Optional<TechnicianInventory> handle(AddComponentStockCommand command);

  /**
   * Handles updating a component stock item in an existing inventory.
   *
   * @param command The command to update stock details.
   * @return An Optional containing the updated TechnicianInventory, or empty if not found.
   */
  Optional<TechnicianInventory> handle(UpdateComponentStockCommand command);

  /**
   * Handles deleting a component stock item from an inventory.
   *
   * @param command The command to delete the stock item.
   * @return true if the stock item was deleted, false otherwise.
   */
  Boolean handle(DeleteComponentStockCommand command);
}