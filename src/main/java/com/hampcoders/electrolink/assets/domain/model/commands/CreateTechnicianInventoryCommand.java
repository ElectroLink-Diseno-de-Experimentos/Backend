package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

/**
 * Command to create a new inventory for a technician.
 */
public record CreateTechnicianInventoryCommand(TechnicianId technicianId) {
}