package com.hampcoders.electrolink.iam.interfaces.rest.resources;

/**
 * Represents the resource used to request a user sign-in.
 *
 * @param username The user's username.
 * @param password The user's password.
 */
public record SignInResource(String username, String password) {
}