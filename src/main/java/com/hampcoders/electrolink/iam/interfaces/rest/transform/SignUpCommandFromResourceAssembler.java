package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.commands.SignUpCommand;
import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.SignUpResource;

/**
 * Assembler responsible for transforming a SignUpResource into a SignUpCommand.
 */
public class SignUpCommandFromResourceAssembler {

  /**
   * Converts a SignUpResource into a SignUpCommand, applying default role logic if necessary.
   *
   * @param resource The SignUpResource.
   * @return The resulting SignUpCommand.
   */
  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    var roles = resource.roles() != null
        ? resource.roles().stream().map(Role::toRoleFromName).toList()
        : null;

    var validatedRoles = Role.validateRoleSet(roles);

    return new SignUpCommand(resource.username(), resource.password(), validatedRoles);
  }
}