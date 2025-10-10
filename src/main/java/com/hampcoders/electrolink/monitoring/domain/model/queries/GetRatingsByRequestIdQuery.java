package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve all ratings associated with a specific request ID.
 *
 * @param requestId The ID of the service operation request.
 */
public record GetRatingsByRequestIdQuery(Long requestId) {}