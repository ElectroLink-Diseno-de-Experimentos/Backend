package com.hampcoders.electrolink.iam.domain.model.queries;

/**
 * Query to retrieve a specific user by their unique username.
 *
 * @param username The username of the user to retrieve.
 */
public record GetUserByUsernameQuery(String username) {
}