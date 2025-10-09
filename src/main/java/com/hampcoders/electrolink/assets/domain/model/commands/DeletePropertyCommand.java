package com.hampcoders.electrolink.assets.domain.model.commands;

import java.util.UUID;

/**
 * Command to delete an existing property.
 */
public record DeletePropertyCommand(UUID propertyId) {
}