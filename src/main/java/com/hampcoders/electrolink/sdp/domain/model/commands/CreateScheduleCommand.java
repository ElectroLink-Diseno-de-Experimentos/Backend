package com.hampcoders.electrolink.sdp.domain.model.commands;

/**
 * Command to create a new schedule.
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
public record CreateScheduleCommand(
        String technicianId,
        String day,
        String startTime,
        String endTime
) {}
