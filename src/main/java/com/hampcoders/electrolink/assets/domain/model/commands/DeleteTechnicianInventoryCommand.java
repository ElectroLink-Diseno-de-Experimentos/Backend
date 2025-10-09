package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import java.util.UUID;

/**
 * Command to delete a technician's inventory.
 */
public record DeleteTechnicianInventoryCommand(Long technicianId) {
}