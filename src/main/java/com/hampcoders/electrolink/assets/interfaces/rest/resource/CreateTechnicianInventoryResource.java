package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Resource used for initiating the creation of a new technician inventory.
 */
public record CreateTechnicianInventoryResource(
    @NotNull Long technicianId
) {}