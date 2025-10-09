package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateTechnicianInventoryCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentStockCommand;
import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.InventoryStockList;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;

/**
 * Represents the inventory aggregate root managed by a specific technician.
 */
@Entity
@Table(name = "technician_inventories")
@Getter
public class TechnicianInventory extends AuditableAbstractAggregateRootNoId<TechnicianInventory> {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "technician_id", nullable = false)
  private Long technicianId;

  @Embedded
  private final InventoryStockList stockList;

  /**
   * Default constructor initializes the stock list.
   */
  public TechnicianInventory() {
    this.stockList = new InventoryStockList();
  }

  /**
   * Constructs a TechnicianInventory with a specific technician ID.
   *
   * @param technicianId The ID of the technician.
   */
  public TechnicianInventory(final Long technicianId) {
    this();
    this.technicianId = technicianId;
  }

  /**
   * Constructs a TechnicianInventory from a creation command.
   *
   * @param command The command containing the technician ID.
   */
  public TechnicianInventory(final CreateTechnicianInventoryCommand command) {
    this();
    this.technicianId = command.technicianId().technicianId();
  }

  /**
   * Adds a new component and its stock quantity to the inventory.
   *
   * @param component The component to add.
   * @param quantity The quantity available.
   * @param threshold The alert threshold.
   */
  public void addToStock(final Component component, final int quantity, final int threshold) {
    this.stockList.addItem(this, component, quantity, threshold);
  }

  /**
   * Gets the current list of component stocks in the inventory.
   *
   * @return A list of ComponentStock entities.
   */
  public List<ComponentStock> getComponentStocks() {
    return this.stockList.getItems();
  }

  /**
   * Updates the stock and threshold for an existing component item.
   *
   * @param command The command containing the new stock and threshold values.
   * @return true if the item was found and updated, false otherwise.
   */
  public boolean updateStockItem(final UpdateComponentStockCommand command) {
    final Optional<ComponentStock> stockToUpdate = this.stockList.getItems().stream()
        .filter(stock -> stock.getComponent().getComponentUid().equals(command.componentId()))
        .findFirst();

    if (stockToUpdate.isEmpty()) {
      return false;
    }

    final ComponentStock stock = stockToUpdate.get();
    stock.updateQuantity(command.newQuantity());
    stock.updateAlertThreshold(command.newAlertThreshold());
    stock.updateLastUpdated(new Date());

    return false;
  }

  /**
   * Removes a stock item for a specific component from the inventory.
   *
   * @param componentId The ID of the component stock to remove.
   * @return true if the item was removed, false otherwise.
   */
  public boolean removeStockItem(final Long componentId) {
    return this.stockList.getItems().removeIf(
        stock -> stock.getComponent().getComponentUid().equals(componentId));
  }
}