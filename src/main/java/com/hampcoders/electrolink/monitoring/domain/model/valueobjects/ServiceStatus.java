package com.hampcoders.electrolink.monitoring.domain.model.valueobjects;

/**
 * Enum representing the possible states of a service operation.
 */
public enum ServiceStatus {
  /** The service operation is created but not yet started. */
  PENDING,

  /** The service operation is currently in progress. */
  IN_PROGRESS,

  /** The service operation has been completed successfully. */
  COMPLETED,

  /** The service operation was canceled before completion. */
  CANCELLED
}
