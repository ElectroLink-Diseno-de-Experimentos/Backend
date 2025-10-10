package com.hampcoders.electrolink.assets.domain.services;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import java.util.Optional;

/**
 * Defines the command handling service for the Component aggregate.
 */
public interface ComponentCommandService {
  /**
   * Handles the creation of a new component.
   *
   * @param command The command to create the component.
   * @return The ID of the newly created component.
   */
  ComponentId handle(CreateComponentCommand command);

  /**
   * Handles the update of an existing component.
   *
   * @param command The command to update the component.
   * @return An Optional containing the updated Component, or empty if not found.
   */
  Optional<Component> handle(UpdateComponentCommand command);

  /**
   * Handles the deletion of a component.
   *
   * @param command The command to delete the component.
   * @return true if the component was deleted, false otherwise.
   */
  Boolean handle(DeleteComponentCommand command);
}