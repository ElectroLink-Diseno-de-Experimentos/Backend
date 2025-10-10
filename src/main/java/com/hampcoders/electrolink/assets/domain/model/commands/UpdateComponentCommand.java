package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import java.util.UUID;

/**
 * Command to update mutable information of an existing component.
 */
public record UpdateComponentCommand(Long componentId, String name, String description,
                                     Long componentTypeId, Boolean isActive) {
}