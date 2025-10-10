package com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Request entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
  /**
   * Finds all requests associated with a specific client ID.
   *
   * @param clientId the ID of the client
   *
   * @return a list of requests for the given client
   */
  List<Request> findByClientId(String clientId);
}
