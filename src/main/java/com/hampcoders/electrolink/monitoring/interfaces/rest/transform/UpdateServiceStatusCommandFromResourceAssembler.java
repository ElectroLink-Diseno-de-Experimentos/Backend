package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateServiceStatusCommand;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateServiceStatusResource;

/**
 * Assembler responsible for converting {@link UpdateServiceStatusResource} objects
 * into {@link UpdateServiceStatusCommand} objects.
 */
public class UpdateServiceStatusCommandFromResourceAssembler {

  /**
   * Converts an UpdateServiceStatusResource into an UpdateServiceStatusCommand.
   *
   * @param resource The resource object containing service status update data.
   * @return The corresponding UpdateServiceStatusCommand.
   */
  public static UpdateServiceStatusCommand toCommandFromResource(
      UpdateServiceStatusResource resource) {
    return new UpdateServiceStatusCommand(
        resource.serviceOperationId(),
        resource.newStatus()
    );
  }
}