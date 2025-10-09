package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import java.time.OffsetDateTime;

/**
 * Command to initiate and create a new service operation.
 *
 * @param requestId The unique identifier for this operation request.
 * @param technicianId The ID of the technician assigned to the operation.
 * @param startedAt The time the operation started.
 * @param completedAt The time the operation was completed (can be null).
 * @param currentStatus The initial status of the operation.
 */
public record CreateServiceOperationCommand(
    RequestId requestId,
    TechnicianId technicianId,
    OffsetDateTime startedAt,
    OffsetDateTime completedAt,
    ServiceStatus currentStatus) {
}