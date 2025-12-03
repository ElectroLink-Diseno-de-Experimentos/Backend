package com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Rating;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for the {@link Rating} aggregate.
 */
public interface RatingRepository extends JpaRepository<Rating, Long> {

  /**
   * Finds a rating by its specific RatingId Value Object.
   *
   * @param id The RatingId to search for.
   * @return An Optional containing the found Rating, or empty if not found.
   */
  Optional<Rating> findById(Long id);

  /**
   * Finds all ratings given to a specific technician.
   *
   * @param technicianId The TechnicianId to filter by.
   * @return A list of ratings associated with the technician.
   */
  List<Rating> findByTechnicianId(TechnicianId technicianId);

  /**
   * Finds all featured ratings for a specific technician.
   *
   * @param technicianId The TechnicianId to filter by.
   * @param isFeatured   The featured status.
   * @return A list of featured ratings.
   */
  List<Rating> findByTechnicianIdAndIsFeatured(TechnicianId technicianId, Boolean isFeatured);

  /**
   * Finds all ratings associated with a specific service operation request ID.
   *
   * @param requestId The RequestId to filter by.
   * @return A list of ratings associated with the request.
   */
  List<Rating> findByRequestId(RequestId requestId);
}
