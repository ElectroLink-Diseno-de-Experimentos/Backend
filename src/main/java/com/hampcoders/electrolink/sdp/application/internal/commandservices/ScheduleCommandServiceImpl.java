package com.hampcoders.electrolink.sdp.application.internal.commandservices;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.services.ScheduleCommandService;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementation of the ScheduleCommandService interface.
 * This service handles commands related to creating, updating, and deleting Schedule aggregates.
 */
@Service
public class ScheduleCommandServiceImpl implements ScheduleCommandService {

  private final ScheduleRepository scheduleRepository;

  /**
   * Constructs a new ScheduleCommandServiceImpl with the given ScheduleRepository.
   *
   * @param scheduleRepository The repository for accessing schedule data.
   */
  public ScheduleCommandServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  /**
   * Handles the CreateScheduleCommand to create a new schedule.
   *
   * @param command The command containing the data for the new schedule.
   *
   * @return The ID of the created schedule.
   */
  @Override
  @Transactional
  public Long handle(CreateScheduleCommand command) {
    var schedule = new ScheduleAggregate(
        command.technicianId(),
        command.day(),
        command.startTime(),
        command.endTime()
    );
    return scheduleRepository.save(schedule).getId();
  }

  /**
   * Handles the UpdateScheduleCommand to update an existing schedule.
   *
   * @param command The command containing the updated data for the schedule.
   *
   * @throws IllegalArgumentException if the schedule is not found.
   */
  @Override
  @Transactional
  public void handle(UpdateScheduleCommand command) {
    var schedule = scheduleRepository.findById(command.scheduleId())
        .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
    schedule.updateFrom(command.day(), command.startTime(), command.endTime());
    scheduleRepository.save(schedule);
  }

  /**
   * Handles the DeleteScheduleCommand to delete a schedule.
   *
   * @param command The command containing the ID of the schedule to delete.
   *
   * @throws IllegalArgumentException if the schedule is not found.
   */
  @Override
  @Transactional
  public void handle(DeleteScheduleCommand command) {
    if (!scheduleRepository.existsById(command.scheduleId())) {
      throw new IllegalArgumentException("Schedule not found");
    }
    scheduleRepository.deleteById(command.scheduleId());
  }
}
