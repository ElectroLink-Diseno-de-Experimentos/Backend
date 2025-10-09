package com.hampcoders.electrolink.sdp.interfaces.rest;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindSchedulesByTechnicianIdQuery;
import com.hampcoders.electrolink.sdp.domain.services.ScheduleCommandService;
import com.hampcoders.electrolink.sdp.domain.services.ScheduleQueryService;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.UpdateScheduleResource;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ScheduleController handles HTTP requests related to schedules.
 * It provides endpoints to create, update, delete, and retrieve schedules for technicians.
 */
@RestController
@RequestMapping("/api/v1")
public class ScheduleController {

  private final ScheduleCommandService commandService;
  private final ScheduleQueryService queryService;

  /**
   * Constructor for ScheduleController.
   *
   * @param commandService The service for handling schedule commands.
   *
   * @param queryService The service for handling schedule queries.
   */
  public ScheduleController(ScheduleCommandService commandService,
                            ScheduleQueryService queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  /**
   * Retrieves all schedules for a specific technician.
   *
   * @param technicianId The ID of the technician.
   *
   * @return A list of schedules for the technician.
   */
  @GetMapping("/technicians/{technicianId}/schedules")
  public ResponseEntity<List<ScheduleAggregate>> getByTechnician(@PathVariable
                                                                   String technicianId) {
    var query = new FindSchedulesByTechnicianIdQuery(technicianId);
    var result = queryService.handle(query);
    return ResponseEntity.ok(result);
  }

  /**
   * Creates a new schedule.
   *
   * @param resource The resource containing schedule creation data.
   *
   * @return The ID of the newly created schedule.
   */
  @PostMapping("/schedules")
  public ResponseEntity<Long> create(@RequestBody CreateScheduleResource resource) {
    var command = new CreateScheduleCommand(
        resource.technicianId(),
        resource.day(),
        resource.startTime(),
        resource.endTime()
    );
    Long createdId = commandService.handle(command);
    return ResponseEntity.ok(createdId);
  }

  /**
   * Updates an existing schedule.
   *
   * @param scheduleId The ID of the schedule to update.
   *
   * @param resource The resource containing updated schedule data.
   *
   * @return An OK response if the update is successful.
   */
  @PutMapping("/schedules/{scheduleId}")
  public ResponseEntity<?> update(@PathVariable Long scheduleId,
                                  @RequestBody UpdateScheduleResource resource) {
    var command = new UpdateScheduleCommand(
        scheduleId,
        resource.technicianId(),
        resource.day(),
        resource.startTime(),
        resource.endTime()
    );
    commandService.handle(command);
    return ResponseEntity.ok().build();
  }

  /**
   * Deletes a schedule.
   *
   * @param scheduleId The ID of the schedule to delete.
   *
   * @return An OK response if the deletion is successful.
   */
  @DeleteMapping("/schedules/{scheduleId}")
  public ResponseEntity<?> delete(@PathVariable Long scheduleId) {
    var command = new DeleteScheduleCommand(scheduleId);
    commandService.handle(command);
    return ResponseEntity.ok().build();
  }
}
