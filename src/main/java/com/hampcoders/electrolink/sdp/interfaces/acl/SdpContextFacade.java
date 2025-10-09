package com.hampcoders.electrolink.sdp.interfaces.acl;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.RequestCommandService;
import com.hampcoders.electrolink.sdp.domain.services.RequestQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Facade for the Service Delivery Platform (SDP) context.
 * This class provides a simplified interface for interacting with the request services.
 * It acts as an Anti-Corruption Layer (ACL) to protect the domain from external changes.
 */
@Service
public class SdpContextFacade {

  private final RequestCommandService requestCommandService;
  private final RequestQueryService requestQueryService;

  /**
   * Constructs a new SDPContextFacade with the required services.
   *
   * @param requestCommandService The service for handling request commands.
   *
   * @param requestQueryService   The service for handling request queries.
   */
  public SdpContextFacade(RequestCommandService requestCommandService,
                          RequestQueryService requestQueryService) {
    this.requestCommandService = requestCommandService;
    this.requestQueryService = requestQueryService;
  }

  /**
   * Fetches a request by its unique ID.
   *
   * @param requestId The ID of the request to fetch.
   *
   * @return An Optional containing the Request if found, otherwise empty.
   */
  public Optional<Request> fetchRequestById(Long requestId) {
    var query = new FindRequestByIdQuery(requestId);
    return requestQueryService.handle(query);
  }

  /**
   * Fetches all requests associated with a specific client ID.
   *
   * @param clientId The ID of the client.
   *
   * @return A list of requests for the given client.
   */
  public List<Request> fetchRequestsByClientId(String clientId) {
    var query = new FindRequestsByClientIdQuery(clientId);
    return requestQueryService.handle(query);
  }

  /**
   * Creates a new service request.
   *
   * @param resource The resource containing the data for the new request.
   *
   * @return The ID of the created request.
   */
  public Long createRequest(CreateRequestResource resource) {
    var command = new CreateRequestCommand(resource);
    return requestCommandService.handle(command).getId();
  }

  /**
   * Updates an existing service request.
   *
   * @param requestId The ID of the request to update.
   *
   * @param resource  The resource containing the updated data.
   *
   * @return The ID of the updated request.
   */
  public Long updateRequest(Long requestId, CreateRequestResource resource) {
    var command = new UpdateRequestCommand(requestId, resource);
    return requestCommandService.handle(command).getId();
  }

  /**
   * Deletes a service request by its ID.
   *
   * @param requestId The ID of the request to delete.
   */
  public void deleteRequest(Long requestId) {
    var command = new DeleteRequestCommand(requestId);
    requestCommandService.handle(command);
  }
}
