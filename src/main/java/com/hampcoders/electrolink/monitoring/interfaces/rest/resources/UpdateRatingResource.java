package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent the data required to update an existing rating.
 *
 * @param ratingId The unique ID of the rating to update.
 * @param score The new rating score (optional update).
 * @param comment The new optional comment for the rating (optional update).
 */
public record UpdateRatingResource(
                                    Long ratingId,
                                    Integer score,
                                    String comment
) {}