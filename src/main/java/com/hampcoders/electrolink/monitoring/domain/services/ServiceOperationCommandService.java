package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.CreateServiceOperationCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;

/**
 * Defines the commands available for managing ServiceOperation entities.
 */
public interface ServiceOperationCommandService {

  /**
   * Handles the creation of a new service operation.
   *
   * @param command The command containing the details for the new operation.
   * @return The RequestId of the newly created service operation.
   */
  Long handle(CreateServiceOperationCommand command);

  /**
   * Handles the update of a service operation's status.
   *
   * @param command The command containing the ID and the new status.
   */
  void handle(UpdateServiceStatusCommand command);
}