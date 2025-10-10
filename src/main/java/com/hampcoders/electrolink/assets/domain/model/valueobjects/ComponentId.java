package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.io.Serializable;

/**
 * Represents a unique identifier for a component.
 */
public record ComponentId(Long componentId) implements Serializable {
  /**
   * Primary constructor for ComponentId.
   *
   * @param componentId The component ID value, must be a positive number.
   * @throws IllegalArgumentException if the ID is null or not positive.
   */
  public ComponentId {
    if (componentId == null || componentId <= 0) {
      throw new IllegalArgumentException("Component ID cannot be null");
    }
  }
}