package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve a specific service operation by its request ID.
 *
 * @param serviceOperationId The ID of the service operation request to retrieve.
 */
public record GetServiceOperationByIdQuery(Long serviceOperationId) {}