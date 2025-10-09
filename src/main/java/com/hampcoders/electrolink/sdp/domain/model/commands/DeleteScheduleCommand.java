package com.hampcoders.electrolink.sdp.domain.model.commands;

/**
 * Command to delete a schedule.
 *
 * @param scheduleId The ID of the schedule to delete.
 */
public record DeleteScheduleCommand(Long scheduleId) {}
