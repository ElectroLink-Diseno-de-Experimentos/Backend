package com.hampcoders.electrolink.sdp.domain.model.aggregates;

import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a technician's schedule as an aggregate root.
 */
@Entity
@Getter
@NoArgsConstructor
public class ScheduleAggregate extends AuditableAbstractAggregateRoot<ScheduleAggregate> {

  private String technicianId;

  private String day;
  private String startTime;
  private String endTime;

  /**
   * Constructs a new ScheduleAggregate.
   *
   * @param technicianId The ID of the technician.
   *
   * @param day          The day of the week for the schedule entry.
   *
   * @param startTime    The start time of the availability.
   *
   * @param endTime      The end time of the availability.
   *
   */
  public ScheduleAggregate(String technicianId, String day, String startTime, String endTime) {
    this.technicianId = technicianId;
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Updates the schedule's data.
   *
   * @param day       The new day of the week.
   *
   * @param startTime The new start time.
   *
   * @param endTime   The new end time.
   *
   */
  public void updateFrom(String day, String startTime, String endTime) {
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
