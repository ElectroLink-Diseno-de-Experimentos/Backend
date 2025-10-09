package com.hampcoders.electrolink.sdp.interfaces.rest;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindRequestsByClientIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.RequestCommandService;
import com.hampcoders.electrolink.sdp.domain.services.RequestQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;
import com.hampcoders.electrolink.shared.interfaces.rest.resources.MessageResource;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing service requests.
 * Provides endpoints for creating, retrieving, updating, and deleting requests.
 */
@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

  private final RequestCommandService requestCommandService;
  private final RequestQueryService requestQueryService;

  /**
   * Constructs a new RequestController with the required services.
   *
   * @param requestCommandService the service for handling request commands
   *
   * @param requestQueryService the service for handling request queries
   */
  public RequestController(RequestCommandService requestCommandService,
                           RequestQueryService requestQueryService) {
    this.requestCommandService = requestCommandService;
    this.requestQueryService = requestQueryService;
  }

  /**
   * Creates a new service request.
   *
   * @param resource the resource containing the request data
   *
   * @return a ResponseEntity containing a message with the created request ID
   */
  @PostMapping
  public ResponseEntity<MessageResource> createRequest(
      @RequestBody CreateRequestResource resource) {
    var command = new CreateRequestCommand(resource);
    Request savedRequest = requestCommandService.handle(command);
    return new ResponseEntity<>(new MessageResource("Request created with ID: "
        + savedRequest.getId()),
        HttpStatus.CREATED);
  }

  /**
   * Retrieves a request by its ID.
   *
   * @param id the ID of the request to retrieve
   *
   * @return a ResponseEntity containing the request if found, or 404 if not found
   */
  @GetMapping("/{id}")
  public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
    var query = new FindRequestByIdQuery(id);
    return requestQueryService.handle(query)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Retrieves all requests associated with a specific client.
   *
   * @param clientId the ID of the client whose requests to retrieve
   *
   * @return a ResponseEntity containing a list of requests for the specified client
   */
  @GetMapping("/clients/{clientId}/requests")
  public ResponseEntity<List<Request>> getRequestsByClient(@PathVariable String clientId) {
    var query = new FindRequestsByClientIdQuery(clientId);
    return ResponseEntity.ok(requestQueryService.handle(query));
  }

  /**
   * Updates an existing request.
   *
   * @param id the ID of the request to update
   *
   * @param resource the resource containing the updated request data
   *
   * @return a ResponseEntity containing a success message
   */
  @PutMapping("/{id}")
  public ResponseEntity<MessageResource> updateRequest(
      @PathVariable Long id,
      @RequestBody CreateRequestResource resource) {
    var command = new UpdateRequestCommand(id, resource);
    requestCommandService.handle(command);
    return ResponseEntity.ok(new MessageResource("Request updated successfully."));
  }

  /**
   * Deletes a request by its ID.
   *
   * @param id the ID of the request to delete
   *
   * @return a ResponseEntity containing a success message
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<MessageResource> deleteRequest(@PathVariable Long id) {
    var command = new DeleteRequestCommand(id);
    requestCommandService.handle(command);
    return ResponseEntity.ok(new MessageResource("Request deleted successfully."));
  }
}
