package com.hampcoders.electrolink.assets.domain.model.queries;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;

/**
 * Query to retrieve the details of a specific stock item in a technician's inventory.
 */
public record GetStockItemDetailsQuery(TechnicianId technicianId, ComponentId componentId) {
}