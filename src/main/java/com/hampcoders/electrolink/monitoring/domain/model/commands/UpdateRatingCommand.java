package com.hampcoders.electrolink.monitoring.domain.model.commands;

/**
 * Command to request an update to an existing rating's score and/or comment.
 *
 * @param ratingId The ID of the rating to be updated.
 * @param score The new score value.
 * @param comment The new comment text.
 */
public record UpdateRatingCommand(Long ratingId, int score, String comment) {}