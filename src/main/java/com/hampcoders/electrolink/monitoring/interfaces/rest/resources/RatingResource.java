package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent a retrieved Rating entity for API consumption.
 *
 * @param id The unique ID of the rating.
 * @param requestId The ID of the service operation request being rated.
 * @param score The rating score (e.g., 1-5).
 * @param comment Optional comment provided with the rating.
 * @param raterId The identifier of the user/system that provided the rating.
 * @param technicianId The ID of the technician being rated.
 */
public record RatingResource(
                              Long id,
                              Long requestId,
                              Integer score,
                              String comment,
                              String raterId,
                              Long technicianId
) {}