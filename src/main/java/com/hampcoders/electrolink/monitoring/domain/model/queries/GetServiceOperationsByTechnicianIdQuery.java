package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve all service operations associated with a specific technician ID.
 *
 * @param technicianId The ID of the technician whose service operations should be retrieved.
 */
public record GetServiceOperationsByTechnicianIdQuery(Long technicianId) {}