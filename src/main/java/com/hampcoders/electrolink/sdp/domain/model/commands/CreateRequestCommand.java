package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateRequestResource;

/**
 * Command to create a new service request.
 *
 * @param resource The resource containing the data for the new request.
 */
public record CreateRequestCommand(CreateRequestResource resource) {

}
