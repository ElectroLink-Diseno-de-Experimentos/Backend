package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;

/**
 * Service interface for handling commands related to services.
 */
public interface ServiceCommandService {
  /**
   * Handles the CreateServiceCommand to create a new service.
   *
   * @param command The command containing the data for the new service.
   *
   * @return The ID of the created service.
   *
   */
  Long handle(CreateServiceCommand command);

  /**
   * Handles the UpdateServiceCommand to update an existing service.
   *
   * @param command The command containing the updated data for the service.
   */
  void handle(UpdateServiceCommand command);

  /**
   * Handles the DeleteServiceCommand to delete a service.
   *
   * @param command The command containing the ID of the service to delete.
   */
  void handle(DeleteServiceCommand command);
}
