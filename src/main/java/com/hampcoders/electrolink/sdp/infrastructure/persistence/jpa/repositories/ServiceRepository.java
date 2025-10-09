package com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ServiceEntity entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
