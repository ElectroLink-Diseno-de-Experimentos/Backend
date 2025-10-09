package com.hampcoders.electrolink.assets.domain.model.valueobjects;

/**
 * Represents a geographical district.
 */
public record District(String name) {

  /**
   * Primary constructor for District.
   *
   * @param name The name of the district, cannot be null or empty.
   * @throws IllegalArgumentException if the name is null or blank.
   */
  public District {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("District name cannot be null or empty");
    }
  }

  /**
   * Default constructor that creates an empty district.
   */
  public District() {
    this("");
  }
}