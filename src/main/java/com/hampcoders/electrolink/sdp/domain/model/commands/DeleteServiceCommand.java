package com.hampcoders.electrolink.sdp.domain.model.commands;

/**
 * Command to delete a service.
 *
 * @param serviceId The ID of the service to delete.
 */
public record DeleteServiceCommand(Long serviceId) {}
