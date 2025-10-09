package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling queries related to requests.
 */
public interface RequestQueryService {
  /**
   * Handles the FindRequestByIdQuery to retrieve a request by its ID.
   *
   * @param query The query containing the request ID.
   *
   * @return An Optional containing the Request if found, otherwise empty.
   *
   */
  Optional<Request> handle(FindRequestByIdQuery query);

  /**
   * Handles the FindRequestsByClientIdQuery to retrieve all requests for a specific client.
   *
   * @param query The query containing the client ID.
   *
   * @return A list of requests for the given client.
   *
   */
  List<Request> handle(FindRequestsByClientIdQuery query);
}
