package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent the data required to update the status
 * of an existing service operation.
 *
 * @param serviceOperationId The ID of the service operation request to update.
 * @param newStatus The new status to be applied (e.g., IN_PROGRESS, COMPLETED).
 */
public record UpdateServiceStatusResource(
                                           Long serviceOperationId,
                                           String newStatus
) {}