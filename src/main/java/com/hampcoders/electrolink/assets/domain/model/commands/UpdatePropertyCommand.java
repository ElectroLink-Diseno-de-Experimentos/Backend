package com.hampcoders.electrolink.assets.domain.model.commands;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.Address;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.District;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.Region;
import java.util.UUID;

/**
 * Command to update the address, region, and district of an existing property.
 */
public record UpdatePropertyCommand(UUID propertyId, Address address, Region region,
                                    District district) {
}