package com.hampcoders.electrolink.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Represents the resource returned for a user, excluding sensitive information like the password.
 *
 * @param id The user's ID.
 * @param username The user's username.
 * @param roles The list of roles assigned to the user.
 */
public record UserResource(Long id, String username, List<String> roles) {
}