package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import java.util.UUID;

/**
 * Command to create a new stock item of a component in a technician's inventory.
 */
public record CreateComponentStockCommand(Long technicianId, Long componentId, int quantity,
                                          int alertThreshold) {
}