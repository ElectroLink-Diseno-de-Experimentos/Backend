package com.hampcoders.electrolink.monitoring.domain.model.commands;

/**
 * Command to request an update to the status of an existing service operation.
 *
 * @param serviceOperationId The ID of the service operation to update.
 * @param newStatus The string representation of the new status (e.g., "COMPLETED").
 */
public record UpdateServiceStatusCommand(Long serviceOperationId, String newStatus) {}