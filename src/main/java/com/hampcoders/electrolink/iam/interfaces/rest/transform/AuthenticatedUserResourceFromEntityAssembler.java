package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.AuthenticatedUserResource;

/**
 * Assembler responsible for transforming a User entity
 *     and a token into an AuthenticatedUserResource.
 */
public class AuthenticatedUserResourceFromEntityAssembler {

  /**
   * Converts a User entity and a token into an AuthenticatedUserResource.
   *
   * @param user The User entity.
   * @param token The JWT token string.
   * @return The AuthenticatedUserResource.
   */
  public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
    return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
  }
}