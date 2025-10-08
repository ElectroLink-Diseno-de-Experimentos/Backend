package com.hampcoders.electrolink.iam.domain.model.commands;

/**
 * Command to request user sign-in.
 *
 * @param username The username provided by the user.
 * @param password The password provided by the user.
 */
public record SignInCommand(String username, String password) {
}