package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;

/**
 * Command to update an existing service request.
 *
 * @param requestId The ID of the request to update.
 *
 * @param resource  The resource containing the updated data.
 *
 */
public record UpdateRequestCommand(Long requestId, CreateRequestResource resource) {
}
