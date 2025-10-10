package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve a specific rating by its ID.
 *
 * @param ratingId The unique ID of the rating to retrieve.
 */
public record GetRatingByIdQuery(Long ratingId) {}
