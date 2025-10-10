package com.hampcoders.electrolink.sdp.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a quantity of a component.
 */
@Entity
@Getter
@NoArgsConstructor
public class ComponentQuantity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String componentId;
  private int quantity;

  /**
   * Constructor for ComponentQuantity.
   *
   * @param componentId The ID of the component.
   *
   * @param quantity The quantity of the component.
   *
   */
  public ComponentQuantity(String componentId, int quantity) {
    this.componentId = componentId;
    this.quantity = quantity;
  }
}
