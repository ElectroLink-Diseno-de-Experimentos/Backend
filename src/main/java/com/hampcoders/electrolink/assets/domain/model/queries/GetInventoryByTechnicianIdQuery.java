package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

/**
 * Query to retrieve the inventory belonging to a specific technician ID.
 */
public record GetInventoryByTechnicianIdQuery(TechnicianId technicianId) {
}