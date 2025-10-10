package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.UpdateRatingResource;

/**
 * Assembler responsible for converting {@link UpdateRatingResource} objects
 * into {@link UpdateRatingCommand} objects.
 */
public class UpdateRatingCommandFromResourceAssembler {

  /**
   * Converts an UpdateRatingResource into an UpdateRatingCommand.
   *
   * @param resource The resource object containing rating update data.
   * @return The corresponding UpdateRatingCommand.
   */
  public static UpdateRatingCommand toCommandFromResource(UpdateRatingResource resource) {
    return new UpdateRatingCommand(
        resource.ratingId(),
        resource.score(),
        resource.comment()
    );
  }
}