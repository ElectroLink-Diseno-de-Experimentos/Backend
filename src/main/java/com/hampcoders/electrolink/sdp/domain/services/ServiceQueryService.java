package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling queries related to services.
 */
public interface ServiceQueryService {
  /**
   * Handles the FindServiceByIdQuery to retrieve a service by its ID.
   *
   * @param query The query containing the service ID.
   *
   * @return An Optional containing the ServiceEntity if found, otherwise empty.
   *
   */
  Optional<ServiceEntity> handle(FindServiceByIdQuery query);

  /**
   * Handles the GetAllServicesQuery to retrieve all services.
   *
   * @param query The query to get all services.
   *
   * @return A list of all ServiceEntity objects.
   *
   */
  List<ServiceEntity> handle(GetAllServicesQuery query);
}
