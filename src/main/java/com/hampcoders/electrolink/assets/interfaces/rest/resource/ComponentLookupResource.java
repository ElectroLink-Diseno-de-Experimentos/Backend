package com.hampcoders.electrolink.assets.interfaces.rest.resource;

import java.util.UUID;

/**
 * Resource for simple component lookup (ID and name).
 */
public record ComponentLookupResource(Long id, String name) {}