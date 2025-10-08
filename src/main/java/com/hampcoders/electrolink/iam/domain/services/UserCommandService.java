package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.commands.SignInCommand;
import com.hampcoders.electrolink.iam.domain.model.commands.SignUpCommand;
import java.util.Optional;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Domain service interface for handling user-related commands (sign-in and sign-up).
 */
public interface UserCommandService {
  /**
   * Handles the command for user sign-in.
   *
   * @param command The {@link SignInCommand} instance.
   * @return An {@link Optional} containing the {@link User}
   *     and the generated token if sign-in is successful.
   */
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);

  /**
   * Handles the command for user sign-up.
   *
   * @param command The {@link SignUpCommand} instance.
   * @return An {@link Optional} containing the newly created {@link User} instance.
   */
  Optional<User> handle(SignUpCommand command);
}