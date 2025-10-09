package com.hampcoders.electrolink.sdp.interfaces.rest;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;
import com.hampcoders.electrolink.sdp.domain.services.ServiceCommandService;
import com.hampcoders.electrolink.sdp.domain.services.ServiceQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateServiceResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.transform.ServiceMapper;
import java.util.List;
import java.util.Optional;
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
 * REST controller for managing services.
 * Provides endpoints for creating, retrieving, updating, and deleting services.
 */
@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

  private final ServiceCommandService commandService;
  private final ServiceQueryService queryService;
  private final ServiceQueryService serviceQueryService;

  /**
   * Constructor for ServiceController.
   *
   * @param commandService The service for handling commands.
   * @param queryService The service for handling queries.
   * @param serviceQueryService The service for handling service queries.
   */
  public ServiceController(ServiceCommandService commandService,
                           ServiceQueryService queryService,
                           ServiceQueryService serviceQueryService) {
    this.commandService = commandService;
    this.queryService = queryService;
    this.serviceQueryService = serviceQueryService;
  }

  /**
   * Retrieves all services.
   *
   * @return A response entity with a list of all service resources.
   */
  @GetMapping
  public ResponseEntity<List<CreateServiceResource>> getAllServices() {
    List<ServiceEntity> services = serviceQueryService.handle(new GetAllServicesQuery());
    List<CreateServiceResource> resources = services.stream()
        .map(ServiceMapper::toResource)
        .toList();
    return ResponseEntity.ok(resources);
  }

  /**
   * Creates a new service.
   *
   * @param resource The resource containing the data for the new service.
   * @return A response entity with the ID of the created service.
   */
  @PostMapping
  public ResponseEntity<Long> create(@RequestBody CreateServiceResource resource) {
    CreateServiceCommand command = ServiceMapper.toCreateCommand(resource);
    Long id = commandService.handle(command);
    return ResponseEntity.ok(id);
  }

  /**
   * Updates an existing service.
   *
   * @param serviceId The ID of the service to update.
   * @param resource The resource with the updated data.
   * @return A response entity indicating success.
   */
  @PutMapping("/{serviceId}")
  public ResponseEntity<?> update(@PathVariable Long serviceId,
                                  @RequestBody CreateServiceResource resource) {
    UpdateServiceCommand command = ServiceMapper.toUpdateCommand(serviceId, resource);
    commandService.handle(command);
    return ResponseEntity.ok().build();
  }

  /**
   * Deletes a profile.
   *
   * @param serviceId The ID of the service to delete.
   * @return A response entity indicating success.
   */
  @DeleteMapping("/{serviceId}")
  public ResponseEntity<?> delete(@PathVariable Long serviceId) {
    DeleteServiceCommand command = new DeleteServiceCommand(serviceId);
    commandService.handle(command);
    return ResponseEntity.ok().build();
  }

  /**
   * Retrieves a service by its ID.
   *
   * @param serviceId The ID of the service.
   * @return A response entity with the service entity, or not found if it doesn't exist.
   */
  @GetMapping("/{serviceId}")
  public ResponseEntity<ServiceEntity> getById(@PathVariable Long serviceId) {
    var query = new FindServiceByIdQuery(serviceId);
    Optional<ServiceEntity> result = queryService.handle(query);
    return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}