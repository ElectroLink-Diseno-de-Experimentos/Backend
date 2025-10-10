package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;

/**
 * Service interface for handling commands related to schedules.
 */
public interface ScheduleCommandService {
  /**
   * Handles the CreateScheduleCommand to create a new schedule.
   *
   * @param command The command containing the data for the new schedule.
   *
   * @return The ID of the created schedule.
   *
   */
  Long handle(CreateScheduleCommand command);

  /**
   * Handles the UpdateScheduleCommand to update an existing schedule.
   *
   * @param command The command containing the updated data for the schedule.
   *
   */
  void handle(UpdateScheduleCommand command);

  /**
   * Handles the DeleteScheduleCommand to delete a schedule.
   *
   * @param command The command containing the ID of the schedule to delete.
   *
   */
  void handle(DeleteScheduleCommand command);
}
