package com.hampcoders.electrolink.sdp.domain.model.queries;

/**
 * Represents a query to find a request by its ID.
 *
 * @param requestId The ID of the request to find.
 *
 */
public record FindRequestByIdQuery(Long requestId) {}
