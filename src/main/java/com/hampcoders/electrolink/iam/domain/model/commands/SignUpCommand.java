package com.hampcoders.electrolink.iam.domain.model.commands;

import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import java.util.List;

/**
 * Command to request user sign-up.
 *
 * @param username The desired username for the new user.
 * @param password The raw password for the new user.
 * @param roles The list of roles to assign to the new user.
 */
public record SignUpCommand(String username, String password, List<Role> roles) {
}