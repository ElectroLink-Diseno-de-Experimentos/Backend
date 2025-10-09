package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent the data required to create a new rating.
 *
 * @param requestId The ID of the service operation request being rated.
 * @param score The rating score (e.g., 1-5).
 * @param comment Optional comment provided with the rating.
 * @param raterId The identifier of the user/system that provided the rating.
 * @param technicianId The ID of the technician being rated.
 */
public record CreateRatingResource(
                                    Long requestId,
                                    Integer score,
                                    String comment,
                                    String raterId,
                                    Long technicianId
) {}