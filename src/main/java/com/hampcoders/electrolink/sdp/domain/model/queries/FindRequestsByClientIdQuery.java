package com.hampcoders.electrolink.sdp.domain.model.queries;

/**
 * Represents a query to find requests by client ID.
 *
 * @param clientId The ID of the client.
 *
 */
public record FindRequestsByClientIdQuery(String clientId) {}
