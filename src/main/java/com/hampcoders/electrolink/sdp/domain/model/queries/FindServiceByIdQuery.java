package com.hampcoders.electrolink.sdp.domain.model.queries;

/**
 * Represents a query to find a service by its ID.
 *
 * @param serviceId The ID of the service to find.
 *
 */
public record FindServiceByIdQuery(Long serviceId) {}
