package com.hampcoders.electrolink.sdp.domain.model.queries;

/**
 * Represents a query to find a schedule by its ID.
 *
 * @param scheduleId The ID of the schedule to find.
 *
 */
public record FindScheduleByIdQuery(Long scheduleId) {}
