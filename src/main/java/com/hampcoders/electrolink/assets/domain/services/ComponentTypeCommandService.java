package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import java.util.Optional;

/**
 * Defines the command handling service for the ComponentType aggregate.
 */
public interface ComponentTypeCommandService {
  /**
   * Handles the creation of a new component type.
   *
   * @param command The command to create the component type.
   * @return The ID of the newly created component type.
   */
  ComponentTypeId handle(CreateComponentTypeCommand command);

  /**
   * Handles the update of an existing component type.
   *
   * @param command The command to update the component type.
   * @return An Optional containing the updated ComponentType, or empty if not found.
   */
  Optional<ComponentType> handle(UpdateComponentTypeCommand command);

  /**
   * Handles the deletion of a component type.
   *
   * @param command The command to delete the component type.
   * @return true if the component type was deleted, false otherwise.
   */
  Boolean handle(DeleteComponentTypeCommand command);
}