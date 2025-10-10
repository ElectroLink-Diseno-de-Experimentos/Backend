package com.hampcoders.electrolink.assets.domain.model.valueobjects;

/**
 * Enumeration representing the possible statuses for a property.
 */
public enum PropertyStatuses {
  ACTIVE(1),
  DEFAULT(2),
  INACTIVE(3);

  private final int value;

  PropertyStatuses(final int value) {
    this.value = value;
  }
}