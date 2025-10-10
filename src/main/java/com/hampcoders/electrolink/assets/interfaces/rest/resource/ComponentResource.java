package com.hampcoders.electrolink.assets.interfaces.rest.resource;

/**
 * Resource representing the details of a Component.
 */
public record ComponentResource(
    String id,
    String name,
    String description,
    boolean isActive,
    Long componentTypeId
) {}