package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve all ratings associated with a specific technician ID.
 *
 * @param technicianId The ID of the technician whose ratings should be retrieved.
 */
public record GetRatingsByTechnicianIdQuery(Long technicianId) {}
