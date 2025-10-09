package com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ScheduleAggregate entities.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleAggregate, Long> {
  List<ScheduleAggregate> findByTechnicianId(String technicianId);
}
