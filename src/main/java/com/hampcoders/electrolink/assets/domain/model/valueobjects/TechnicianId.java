package com.hampcoders.electrolink.assets.domain.model.valueobjects;

/**
 * Represents a unique identifier for a technician.
 */
public record TechnicianId(Long technicianId) {
  /**
   * Primary constructor for TechnicianId.
   *
   * @param technicianId The ID value, must be a positive number.
   * @throws IllegalArgumentException if the ID is null or not positive.
   */
  public TechnicianId {
    if (technicianId == null || technicianId <= 0) {
      throw new IllegalArgumentException("Technician ID must be a positive number.");
    }
  }

  /**
   * Gets the raw value of the technician ID.
   *
   * @return The technician ID as a Long.
   */
  public Long value() {
    return technicianId;
  }
}