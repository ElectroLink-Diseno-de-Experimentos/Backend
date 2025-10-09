package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.commands.SeedRolesCommand;

/**
 * Domain service interface for handling role-related commands.
 */
public interface RoleCommandService {
  /**
   * Handles the command to seed initial roles in the database.
   *
   * @param command The {@link SeedRolesCommand} instance.
   */
  void handle(SeedRolesCommand command);
}