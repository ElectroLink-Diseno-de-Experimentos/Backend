package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve all reports associated with a specific request ID.
 *
 * @param serviceOperationId The ID of the service operation request.
 */
public record GetReportsByServiceOperationIdQuery(Long serviceOperationId) {}