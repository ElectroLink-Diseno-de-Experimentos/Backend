package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.CreateServiceOperationCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ServiceStatus;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateServiceOperationResource;
import java.time.OffsetDateTime;

/**
 * Assembler responsible for converting {@link CreateServiceOperationResource} objects
 * into {@link CreateServiceOperationCommand} objects.
 */
public class CreateServiceOperationCommandFromResourceAssembler {

  /**
   * Converts a CreateServiceOperationResource into a CreateServiceOperationCommand.
   *
   * @param resource The resource object containing service operation creation data.
   * @return The corresponding CreateServiceOperationCommand.
   */
  public static CreateServiceOperationCommand toCommandFromResource(
      CreateServiceOperationResource resource) {
    return new CreateServiceOperationCommand(
        new RequestId(resource.requestId()),
        new TechnicianId(resource.technicianId()),
        OffsetDateTime.now(),
        null,
        ServiceStatus.IN_PROGRESS
    );
  }
}