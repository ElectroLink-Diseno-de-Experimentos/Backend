package com.hampcoders.electrolink.iam.interfaces.rest.resources;

/**
 * Represents the resource returned for an authenticated user.
 *
 * @param id The user's ID.
 * @param username The user's username.
 * @param token The generated bearer token.
 */
public record AuthenticatedUserResource(Long id, String username, String token) {
}