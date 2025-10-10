package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import java.util.UUID;

/**
 * Command to delete a component stock item from a technician's inventory.
 */
public record DeleteComponentStockCommand(Long technicianId, Long componentId) {
}