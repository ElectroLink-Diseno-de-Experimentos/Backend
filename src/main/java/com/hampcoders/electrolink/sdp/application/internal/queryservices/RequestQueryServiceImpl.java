package com.hampcoders.electrolink.sdp.application.internal.queryservices;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.RequestQueryService;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.RequestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of the RequestQueryService interface.
 * This service handles queries related to Request aggregates.
 */
@Service
public class RequestQueryServiceImpl implements RequestQueryService {

  private final RequestRepository requestRepository;

  /**
   * Constructs a new RequestQueryServiceImpl with the given RequestRepository.
   *
   * @param requestRepository The repository for accessing request data.
   */
  public RequestQueryServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  /**
   * Handles the FindRequestByIdQuery to retrieve a request by its ID.
   *
   * @param query The query containing the request ID.
   *
   * @return An Optional containing the Request if found, otherwise empty.
   */
  @Override
  public Optional<Request> handle(FindRequestByIdQuery query) {
    return requestRepository.findById(query.requestId());
  }

  /**
   * Handles the FindRequestsByClientIdQuery to retrieve all requests for a specific client.
   *
   * @param query The query containing the client ID.
   *
   * @return A list of requests for the given client.
   */
  @Override
  public List<Request> handle(FindRequestsByClientIdQuery query) {
    return requestRepository.findByClientId(query.clientId());
  }
}
