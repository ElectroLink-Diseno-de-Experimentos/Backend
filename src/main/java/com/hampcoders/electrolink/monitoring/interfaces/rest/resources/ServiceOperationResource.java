package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.time.OffsetDateTime;

/**
 * Resource used to represent a retrieved ServiceOperation entity for API consumption.
 *
 * @param id The unique ID of the service operation.
 * @param requestId The ID of the request.
 * @param technicianId The ID of the technician assigned to the operation.
 * @param startedAt The timestamp when the service operation started.
 * @param completedAt The timestamp when the service operation was completed.
 * @param currentStatus The current status of the service operation (e.g., PENDING, IN_PROGRESS).
 */
public record ServiceOperationResource(
                                        Long id,
                                        Long requestId,
                                        Long technicianId,
                                        OffsetDateTime startedAt,
                                        OffsetDateTime completedAt,
                                        String currentStatus
) {}