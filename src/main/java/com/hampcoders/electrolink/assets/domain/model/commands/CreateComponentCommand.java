package com.hampcoders.electrolink.assets.domain.model.commands;

import java.util.UUID;

/**
 * Command to create a new component.
 */
public record CreateComponentCommand(UUID componentId, String name,
                                     String description, Long componentTypeId, Boolean isActive) {
}