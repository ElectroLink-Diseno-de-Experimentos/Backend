package com.hampcoders.electrolink.sdp.domain.model.commands;

/**
 * Command to update an existing schedule.
 *
 * @param scheduleId   The ID of the schedule to update.
 *
 * @param technicianId The ID of the technician.
 *
 * @param day          The new day of the week.
 *
 * @param startTime    The new start time.
 *
 * @param endTime      The new end time.
 *
 */
public record UpdateScheduleCommand(
        Long scheduleId,
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
