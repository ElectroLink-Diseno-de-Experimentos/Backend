package com.hampcoders.electrolink.monitoring.domain.model.commands;

/**
 * Command to request the deletion of an existing rating.
 *
 * @param ratingId The ID of the rating to be deleted.
 */
public record DeleteRatingCommand(Long ratingId) {}