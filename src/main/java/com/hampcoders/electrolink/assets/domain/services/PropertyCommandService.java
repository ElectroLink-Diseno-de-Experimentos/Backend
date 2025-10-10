package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.commands.CreatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeletePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;
import java.util.Optional;
import java.util.UUID;

/**
 * Defines the command handling service for the Property aggregate.
 */
public interface PropertyCommandService {
  /**
   * Handles the creation of a new property.
   *
   * @param command The command to create the property.
   * @return The UUID of the newly created property.
   */
  UUID handle(CreatePropertyCommand command);

  /**
   * Handles the update of an existing property.
   *
   * @param command The command to update the property.
   * @return An Optional containing the updated Property, or empty if not found.
   */
  Optional<Property> handle(UpdatePropertyCommand command);

  /**
   * Handles the deletion of a property.
   *
   * @param command The command to delete the property.
   * @return true if the property was deleted, false otherwise.
   */
  Boolean handle(DeletePropertyCommand command);
}