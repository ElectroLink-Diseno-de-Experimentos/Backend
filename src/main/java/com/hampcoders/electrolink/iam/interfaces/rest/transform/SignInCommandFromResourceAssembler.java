package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.commands.SignInCommand;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.SignInResource;

/**
 * Assembler responsible for transforming a SignInResource into a SignInCommand.
 */
public class SignInCommandFromResourceAssembler {

  /**
   * Converts a SignInResource into a SignInCommand.
   *
   * @param signInResource The SignInResource.
   * @return The SignInCommand.
   */
  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}