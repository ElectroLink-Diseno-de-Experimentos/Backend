package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

/**
 * Command to delete an existing component type.
 */
public record DeleteComponentTypeCommand(Long componentTypeId) {
}