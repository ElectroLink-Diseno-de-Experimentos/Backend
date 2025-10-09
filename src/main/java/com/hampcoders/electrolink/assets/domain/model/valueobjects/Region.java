package com.hampcoders.electrolink.assets.domain.model.valueobjects;

/**
 * Represents a geographical region.
 */
public record Region(String name) {

  /**
   * Primary constructor for Region.
   *
   * @param name The name of the region, cannot be null or empty.
   * @throws IllegalArgumentException if the name is null or blank.
   */
  public Region {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Region name cannot be null or empty");
    }

  }

  /**
   * Default constructor that creates an empty region.
   */
  public Region() {
    this("");
  }
}