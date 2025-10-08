package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.UserResource;

/**
 * Assembler responsible for transforming a User entity into a UserResource.
 */
public class UserResourceFromEntityAssembler {

  /**
   * Converts a User entity into a UserResource, mapping the roles to their string names.
   *
   * @param user The User entity.
   * @return The UserResource.
   */
  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUsername(), roles);
  }
}