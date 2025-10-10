package com.hampcoders.electrolink.sdp.domain.model.commands;

/**
 * Command to delete a service request.
 *
 * @param requestId The ID of the request to delete.
 */
public record DeleteRequestCommand(Long requestId) {
}
