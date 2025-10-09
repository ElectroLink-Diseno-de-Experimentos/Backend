package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for the {@link Report} aggregate.
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

  /**
   * Finds a report by its specific ReportId Value Object.
   *
   * @param id The ReportId to search for.
   * @return An Optional containing the found Report, or empty if not found.
   */
  Optional<Report> findById(Long id);

  /**
   * Finds all reports associated with a specific service operation request ID.
   *
   * @param serviceOperationId The RequestId to filter by.
   * @return A list of reports associated with the request.
   */
  List<Report> findByServiceOperationId(Long serviceOperationId);
}