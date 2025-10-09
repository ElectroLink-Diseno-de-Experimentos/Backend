package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import java.io.Serializable;

/**
 * Represents a unique identifier for a component type.
 */
public record ComponentTypeId(Long id) implements Serializable {
  /**
   * Primary constructor for ComponentTypeId.
   *
   * @param id The ID value, must be a positive number.
   * @throws IllegalArgumentException if the ID is null or not positive.
   */
  public ComponentTypeId {
    if (id == null || id <= 0) {
      throw new IllegalArgumentException("Component type ID must be a positive number.");
    }
  }
}