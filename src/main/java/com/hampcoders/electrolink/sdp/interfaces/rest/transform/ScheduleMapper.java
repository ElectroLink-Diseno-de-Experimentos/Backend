package com.hampcoders.electrolink.sdp.interfaces.rest.transform;

import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.entities.Schedule;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.ScheduleResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.UpdateScheduleResource;

/**
 * Mapper class for Schedule entities and resources.
 */
public class ScheduleMapper {

  /**
   * Converts a {@link CreateScheduleResource} to a {@link CreateScheduleCommand}.
   *
   * @param resource The resource to convert.
   * @return The converted command.
   */
  public static CreateScheduleCommand toCommand(CreateScheduleResource resource) {
    return new CreateScheduleCommand(
        resource.technicianId(),
        resource.day(),
        resource.startTime(),
        resource.endTime()
    );
  }

  /**
   * Converts an {@link UpdateScheduleResource} to an {@link UpdateScheduleCommand}.
   *
   * @param id The ID of the schedule to update.
   * @param resource The resource containing the updated data.
   * @return The converted command.
   */
  public static UpdateScheduleCommand toCommand(Long id, UpdateScheduleResource resource) {
    return new UpdateScheduleCommand(
        id,
        resource.technicianId(),
        resource.day(),
        resource.startTime(),
        resource.endTime()
    );
  }

  /**
   * Converts a {@link Schedule} entity to a {@link ScheduleResource}.
   *
   * @param entity The entity to convert.
   * @return The converted resource.
   */
  public static ScheduleResource toResource(Schedule entity) {
    return new ScheduleResource(
        entity.getId(),
        entity.getTechnicianId(),
        entity.getDay(),
        entity.getStartTime(),
        entity.getEndTime()
    );
  }
}
