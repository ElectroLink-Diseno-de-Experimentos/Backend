package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import java.util.List;

/**
 * Command to create a new service.
 *
 * @param name          The name of the service.
 *
 * @param description   A description of the service.
 *
 * @param price         The price of the service.
 *
 * @param estimatedTime The estimated time to complete the service.
 *
 * @param category      The category of the service.
 *
 * @param isVisible     Whether the service is visible to clients.
 *
 * @param createdBy     The ID of the user who created the service.
 *
 * @param policy        The service's policy.
 *
 * @param restriction   The service's restrictions.
 *
 * @param tags          A list of tags associated with the service.
 *
 * @param components    A list of components required for the service.
 *
 */
public record CreateServiceCommand(
        String name,
        String description,
        Double price,
        String estimatedTime,
        String category,
        boolean isVisible,
        String createdBy,
        Policy policy,
        Restriction restriction,
        List<Tag> tags,
        List<ComponentQuantity> components
) {}
