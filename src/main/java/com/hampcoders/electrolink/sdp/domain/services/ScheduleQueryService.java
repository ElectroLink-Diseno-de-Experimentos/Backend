package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindScheduleByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindSchedulesByTechnicianIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for handling queries related to schedules.
 */
public interface ScheduleQueryService {
  /**
   * Handles the FindScheduleByIdQuery to retrieve a schedule by its ID.
   *
   * @param query The query containing the schedule ID.
   *
   * @return An Optional containing the ScheduleAggregate if found, otherwise empty.
   *
   */
  Optional<ScheduleAggregate> handle(FindScheduleByIdQuery query);

  /**
   * Handles the {@link FindSchedulesByTechnicianIdQuery}.
   *
   * @param query The query containing the technician ID.
   * @return A list of schedules for the given technician.
   */
  List<ScheduleAggregate> handle(FindSchedulesByTechnicianIdQuery query);
}
