package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;

/**
 * Command to add a new rating for a service operation.
 *
 * @param requestId The ID of the service operation being rated.
 * @param score The rating score (1-5).
 * @param comment The optional comment.
 * @param raterId The ID of the user submitting the rating.
 * @param technicianId The ID of the technician who performed the service.
 */
public record AddRatingCommand(
    RequestId requestId,
    int score,
    String comment,
    String raterId,
    TechnicianId technicianId
) {}