package com.hampcoders.electrolink.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Represents the resource used to request a new user sign-up.
 *
 * @param username The user's desired username.
 * @param password The user's desired password.
 * @param roles The list of roles to assign to the user (optional).
 */
public record SignUpResource(String username, String password, List<String> roles) {
}