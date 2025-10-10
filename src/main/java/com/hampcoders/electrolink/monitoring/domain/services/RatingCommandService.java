package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteRatingCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.UpdateRatingCommand;

/**
 * Defines the commands available for managing Rating entities.
 * (Note: The interface name 'IRatingCommandService' uses an 'I' prefix, which
 * Checkstyle discourages as an abbreviation but is standard for interfaces.)
 */
public interface RatingCommandService {

  /**
   * Handles the creation of a new rating.
   *
   * @param command The command containing the details for the new rating.
   * @return The ID of the newly created rating.
   */
  Long handle(AddRatingCommand command);

  /**
   * Handles the update of an existing rating.
   *
   * @param command The command containing the ID and new rating details.
   */
  void handle(UpdateRatingCommand command);

  /**
   * Handles the deletion of a specific rating.
   *
   * @param command The command containing the ID of the rating to delete.
   */
  void handle(DeleteRatingCommand command);
}