package com.hampcoders.electrolink.sdp.application.internal.queryservices;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;
import com.hampcoders.electrolink.sdp.domain.services.ServiceQueryService;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ServiceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ServiceQueryService interface.
 * This service handles queries related to ServiceEntity objects.
 */
@Service
public class ServiceQueryServiceImpl implements ServiceQueryService {

  private final ServiceRepository serviceRepository;

  /**
   * Constructs a new ServiceQueryServiceImpl with the given ServiceRepository.
   *
   * @param serviceRepository The repository for accessing service data.
   */
  public ServiceQueryServiceImpl(ServiceRepository serviceRepository) {
    this.serviceRepository = serviceRepository;
  }

  /**
   * Handles the FindServiceByIdQuery to retrieve a service by its ID.
   *
   * @param query The query containing the service ID.
   *
   * @return An Optional containing the ServiceEntity if found, otherwise empty.
   */
  @Override
  public Optional<ServiceEntity> handle(FindServiceByIdQuery query) {
    return serviceRepository.findById(query.serviceId());
  }

  /**
   * Handles the GetAllServicesQuery to retrieve all services.
   *
   * @param query The query to get all services.
   *
   * @return A list of all ServiceEntity objects.
   */
  @Override
  public List<ServiceEntity> handle(GetAllServicesQuery query) {
    return serviceRepository.findAll();
  }
}