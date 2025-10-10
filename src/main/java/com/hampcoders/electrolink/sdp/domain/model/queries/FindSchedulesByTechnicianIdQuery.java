package com.hampcoders.electrolink.sdp.domain.model.queries;

/**
 * Represents a query to find schedules by technician ID.
 *
 * @param technicianId The ID of the technician.
 */
public record FindSchedulesByTechnicianIdQuery(String technicianId) {}
