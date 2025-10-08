package com.hampcoders.electrolink.iam.domain.model.queries;

/**
 * Query to retrieve a specific user by their unique identifier.
 *
 * @param userId The unique ID of the user to retrieve.
 */
public record GetUserByIdQuery(Long userId) {
}