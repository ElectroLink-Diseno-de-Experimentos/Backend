package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.application.internal.commandservices.ServiceOperationCommandServiceImpl;
import com.hampcoders.electrolink.monitoring.application.internal.queryservices.ServiceOperationQueryServiceImpl;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllServiceOperationsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationsByTechnicianIdQuery;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateServiceOperationResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ServiceOperationResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateServiceStatusResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateServiceOperationCommandFromResourceAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.ServiceOperationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for managing service operations, providing endpoints for
 * creation and retrieval.
 */
@RestController
@RequestMapping("/api/v1/service-operations")
public class ServiceOperationsController {

  private final ServiceOperationCommandServiceImpl commandService;
  private final ServiceOperationQueryServiceImpl queryService;

  /**
   * Constructs a ServiceOperationsController with the necessary command and query services.
   *
   * @param commandService The service for handling service operation commands (CUD).
   * @param queryService The service for handling service operation queries (R).
   */
  public ServiceOperationsController(ServiceOperationCommandServiceImpl commandService,
                                     ServiceOperationQueryServiceImpl queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  /**
   * Creates a new service operation.
   *
   * @param resource The data for the new service operation.
   * @return The RequestId of the newly created service operation with HTTP status 201 (Created).
   */
  @Operation(summary = "Create a new service operation")
  @PostMapping
  public ResponseEntity<Long> createServiceOperation(
      @Valid @RequestBody CreateServiceOperationResource resource) {
    var command = CreateServiceOperationCommandFromResourceAssembler
        .toCommandFromResource(resource);
    var id = commandService.handle(command);
    return new ResponseEntity<>(id.getId(), HttpStatus.CREATED);
  }

  /**
   * Retrieves a specific service operation by its ID.
   *
   * @param serviceOperationId The ID of the service operation request to retrieve.
   * @return The requested service operation resource with HTTP status 200 (OK).
   */
  @Operation(summary = "Get a service operation by ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Found successfully"),
      @ApiResponse(responseCode = "404", description = "Not found")
  })
  @GetMapping("/{serviceOperationId}")
  public ResponseEntity<ServiceOperationResource> getById(
      @Parameter(description = "Id of the service operation")
      @PathVariable Long serviceOperationId) {

    var result = queryService.handle(new GetServiceOperationByIdQuery(serviceOperationId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Service operation not found"));

    return ResponseEntity
        .ok(ServiceOperationResourceFromEntityAssembler.toResourceFromEntity(result));
  }

  /**
   * Retrieves all service operations assigned to a specific technician.
   *
   * @param technicianId The ID of the technician.
   * @return A list of service operation resources for the technician with HTTP status 200 (OK).
   */
  @Operation(summary = "Get service operations by technician ID")
  @GetMapping("/technicians/{technicianId}")
  public ResponseEntity<List<ServiceOperationResource>> getServiceOperationsByTechnicianId(
      @Parameter(description = "Id of the technician") @PathVariable Long technicianId
  ) {
    var query = new GetServiceOperationsByTechnicianIdQuery(technicianId);
    var results = queryService.handle(query);
    var resources = results.stream()
        .map(ServiceOperationResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }

  /**
   * Retrieves all service operations available in the system.
   *
   * @return A list of all service operation resources with HTTP status 200 (OK).
   */
  @Operation(summary = "Get all service operations")
  @GetMapping
  public ResponseEntity<List<ServiceOperationResource>> getAll() {
    var results = queryService.handle(new GetAllServiceOperationsQuery());
    var resources = results.stream()
        .map(ServiceOperationResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }

  /**
   * Updates the status of an existing service operation.
   *
   * @param resource The resource containing the request ID and the new status.
   * @return Empty response with HTTP status 204 (No Content).
   */
  @Operation(summary = "Update service operation status")
  @PutMapping("/status")
  public ResponseEntity<Void> updateStatus(
      @Valid @RequestBody UpdateServiceStatusResource resource) {

    var command = new UpdateServiceStatusCommand(
        resource.serviceOperationId(), resource.newStatus());
    commandService.handle(command);
    return ResponseEntity.noContent().build();
  }
}