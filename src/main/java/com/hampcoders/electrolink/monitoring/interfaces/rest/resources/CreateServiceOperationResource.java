package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent the data required to create a new service operation.
 *
 * @param requestId The ID of the initial request that triggered this operation.
 * @param technicianId The ID of the technician assigned to the operation.
 */
public record CreateServiceOperationResource(Long requestId,
                                             Long technicianId
) {}