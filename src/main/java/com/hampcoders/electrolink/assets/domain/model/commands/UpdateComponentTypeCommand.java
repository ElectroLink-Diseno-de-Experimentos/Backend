package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;

/**
 * Command to update the mutable information of an existing component type.
 */
public record UpdateComponentTypeCommand(Long id, String name, String description) {}