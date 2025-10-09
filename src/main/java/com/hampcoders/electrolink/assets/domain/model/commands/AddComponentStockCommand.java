package com.hampcoders.electrolink.assets.domain.model.commands;

/**
 * Command to add a new component stock item to a technician's inventory.
 */
public record AddComponentStockCommand(
    Long technicianId,
    Long componentId,
    int quantity,
    int alertThreshold
) {}