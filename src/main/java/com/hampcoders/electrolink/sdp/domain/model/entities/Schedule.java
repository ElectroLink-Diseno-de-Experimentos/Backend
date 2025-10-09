package com.hampcoders.electrolink.sdp.domain.model.entities;

import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.UpdateScheduleResource;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a technician's schedule.
 * It is an aggregate root.
 */
@Entity
@Getter
@NoArgsConstructor
public class Schedule extends AuditableAbstractAggregateRoot<Schedule> {

  private String technicianId;
  private String day;
  private String startTime;
  private String endTime;

  /**
   * Constructor for Schedule.
   *
   * @param technicianId The technician's ID.
   *
   * @param day The day of the week.
   *
   * @param startTime The start time.
   *
   * @param endTime The end time.
   *
   */
  public Schedule(String technicianId, String day, String startTime, String endTime) {
    this.technicianId = technicianId;
    this.day = day;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Updates the schedule from a command.
   *
   * @param command The update command.
   *
   */
  public void updateFrom(UpdateScheduleCommand command) {
    this.technicianId = command.technicianId();
    this.day = command.day();
    this.startTime = command.startTime();
    this.endTime = command.endTime();
  }

  /**
   * Updates the schedule from a resource.
   *
   * @param resource The update resource.
   *
   */
  public void updateFrom(UpdateScheduleResource resource) {
    this.technicianId = resource.technicianId();
    this.day = resource.day();
    this.startTime = resource.startTime();
    this.endTime = resource.endTime();
  }
}
