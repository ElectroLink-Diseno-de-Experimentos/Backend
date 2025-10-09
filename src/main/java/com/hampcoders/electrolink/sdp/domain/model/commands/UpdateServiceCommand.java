package com.hampcoders.electrolink.sdp.domain.model.commands;

import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import java.util.List;

/**
 * Command to update an existing service.
 *
 * @param serviceId     The ID of the service to update.
 *
 * @param name          The new name of the service.
 *
 * @param description   A new description of the service.
 *
 * @param price         The new price of the service.
 *
 * @param estimatedTime The new estimated time to complete the service.
 *
 * @param category      The new category of the service.
 *
 * @param isVisible     Whether the service is visible to clients.
 *
 * @param createdBy     The ID of the user who created the service.
 *
 * @param policy        The new service's policy.
 *
 * @param restriction   The new service's restrictions.
 *
 * @param tags          A new list of tags associated with the service.
 *
 * @param components    A new list of components required for the service.
 *
 */
public record UpdateServiceCommand(
        Long serviceId,
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
