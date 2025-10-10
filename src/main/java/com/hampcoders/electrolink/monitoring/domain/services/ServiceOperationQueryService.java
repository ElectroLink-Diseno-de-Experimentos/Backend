package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.ServiceOperation;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllServiceOperationsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetServiceOperationsByTechnicianIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query operations available for retrieving ServiceOperation entities.
 */
public interface ServiceOperationQueryService {

  /**
   * Retrieves all existing service operations.
   *
   * @param query The query object (marker).
   * @return A list of all service operations.
   */
  List<ServiceOperation> handle(GetAllServiceOperationsQuery query);

  /**
   * Retrieves a specific service operation by its request ID.
   *
   * @param query The query object containing the request ID.
   * @return An Optional containing the service operation, or empty if not found.
   */
  Optional<ServiceOperation> handle(GetServiceOperationByIdQuery query);

  /**
   * Retrieves all service operations associated with a specific technician ID.
   *
   * @param query The query object containing the technician ID.
   * @return A list of service operations for the specified technician.
   */
  List<ServiceOperation> handle(GetServiceOperationsByTechnicianIdQuery query);
}