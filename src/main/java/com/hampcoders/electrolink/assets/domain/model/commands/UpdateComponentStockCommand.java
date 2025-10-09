package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.TechnicianId;
import java.util.UUID;

/**
 * Command to update the quantity and alert threshold of an existing component stock item.
 */
public record UpdateComponentStockCommand(Long technicianId, Long componentId, int newQuantity,
                                          Integer newAlertThreshold) {}