package com.hampcoders.electrolink.assets.domain.model.entities;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.aggregates.TechnicianInventory;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;

/**
 * Represents a specific stock quantity of a component within a technician's inventory.
 */
@Entity
@Table(name = "component_stocks")
@Getter
public class ComponentStock extends AuditableModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "component_stock_uid", nullable = false, updatable = false)
  private UUID id;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "technician_inventory_id")
  private TechnicianInventory technicianInventory;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "component_id", nullable = false)
  private Component component;

  private int quantityAvailable;

  private int alertThreshold;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdated;

  /**
   * Default constructor required by JPA.
   */
  public ComponentStock() {
  }

  /**
   * Constructs a ComponentStock instance with all necessary fields.
   *
   * @param technicianInventory The inventory to which this stock belongs.
   * @param component The component being stocked.
   * @param quantityAvailable The initial quantity available.
   * @param alertThreshold The threshold for low stock alerts.
   * @param lastUpdated The last time the stock was updated.
   */
  public ComponentStock(final TechnicianInventory technicianInventory, final Component component,
                        final int quantityAvailable, final int alertThreshold,
                        final Date lastUpdated) {
    this.technicianInventory = technicianInventory;
    this.component = component;
    this.quantityAvailable = quantityAvailable;
    this.alertThreshold = alertThreshold;
    this.lastUpdated = lastUpdated;
  }

  /**
   * Updates the available quantity of the component stock.
   *
   * @param newQuantity The new quantity, must be non-negative.
   * @throws IllegalArgumentException if the new quantity is negative.
   */
  public void updateQuantity(final int newQuantity) {
    if (newQuantity < 0) {
      throw new IllegalArgumentException("Quantity cannot be negative.");
    }
    this.quantityAvailable = newQuantity;
  }

  /**
   * Updates the alert threshold for the component stock.
   *
   * @param newAlertThreshold The new alert threshold, must be non-negative.
   * @throws IllegalArgumentException if the new alert threshold is negative.
   */
  public void updateAlertThreshold(final int newAlertThreshold) {
    if (newAlertThreshold < 0) {
      throw new IllegalArgumentException("Alert threshold cannot be negative.");
    }
    this.alertThreshold = newAlertThreshold;
  }

  /**
   * Updates the last updated timestamp.
   *
   * @param date The new date and time of the update.
   */
  public void updateLastUpdated(final Date date) {
    this.lastUpdated = date;
  }
}