package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.RequestId;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.TechnicianId;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateRatingResource;

/**
 * Assembler responsible for converting {@link CreateRatingResource} objects
 * into {@link AddRatingCommand} objects.
 */
public class CreateRatingCommandFromResourceAssembler {

  /**
   * Converts a CreateRatingResource into an AddRatingCommand.
   *
   * @param resource The resource object containing rating creation data.
   * @return The corresponding AddRatingCommand.
   */
  public static AddRatingCommand toCommandFromResource(CreateRatingResource resource) {
    return new AddRatingCommand(
        new RequestId(resource.requestId()),
        resource.score(),
        resource.comment(),
        resource.raterId(),
        new TechnicianId(resource.technicianId())
    );
  }
}