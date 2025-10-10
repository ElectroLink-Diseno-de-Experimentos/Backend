package com.hampcoders.electrolink.sdp.interfaces.rest.transform;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.ComponentQuantityResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.CreateServiceResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.PolicyResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.RestrictionResource;
import com.hampcoders.electrolink.sdp.interfaces.rest.resources.TagResource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between service-related resources and domain models.
 */
public class ServiceMapper {

  /**
   * Converts a {@link CreateServiceResource} to a {@link ServiceEntity} model.
   *
   * @param resource The resource to convert.
   * @return The converted {@link ServiceEntity}.
   */
  public static ServiceEntity toModel(CreateServiceResource resource) {
    Policy policy = new Policy(
        resource.policy().cancellationPolicy(),
        resource.policy().termsAndConditions());
    Restriction restriction = new Restriction(
        resource.restriction().unavailableDistricts(),
        resource.restriction().forbiddenDays(),
        resource.restriction().requiresSpecialCertification()
    );
    List<Tag> tags = resource.tags().stream()
        .map(tag -> new Tag(tag.name()))
        .collect(Collectors.toList());

    List<ComponentQuantity> components = resource.components().stream()
        .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
        .collect(Collectors.toList());

    return new ServiceEntity(
        resource.name(),
        resource.description(),
        resource.basePrice(),
        resource.estimatedTime(),
        resource.category(),
        resource.isVisible(),
        resource.createdBy(),
        policy,
        restriction,
        tags,
        components
    );
  }

  /**
   * Converts a {@link CreateServiceResource} to a {@link CreateServiceCommand}.
   *
   * @param resource The resource to convert.
   * @return The converted {@link CreateServiceCommand}.
   */
  public static CreateServiceCommand toCreateCommand(CreateServiceResource resource) {
    Policy policy = new Policy(
        resource.policy().cancellationPolicy(),
        resource.policy().termsAndConditions());
    Restriction restriction = new Restriction(
        resource.restriction().unavailableDistricts(),
        resource.restriction().forbiddenDays(),
        resource.restriction().requiresSpecialCertification()
    );
    List<Tag> tags = resource.tags().stream()
        .map(tag -> new Tag(tag.name()))
        .collect(Collectors.toList());

    List<ComponentQuantity> components = resource.components().stream()
        .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
        .collect(Collectors.toList());

    return new CreateServiceCommand(
        resource.name(),
        resource.description(),
        resource.basePrice(),
        resource.estimatedTime(),
        resource.category(),
        resource.isVisible(),
        resource.createdBy(),
        policy,
        restriction,
        tags,
        components
    );
  }

  /**
   * Converts a {@link CreateServiceResource} and a service ID to an {@link UpdateServiceCommand}.
   *
   * @param serviceId The ID of the service to be updated.
   * @param resource The resource containing the updated data.
   * @return The converted {@link UpdateServiceCommand}.
   */
  public static UpdateServiceCommand toUpdateCommand(
      Long serviceId,
      CreateServiceResource resource) {
    Policy policy = new Policy(
        resource.policy().cancellationPolicy(),
        resource.policy().termsAndConditions());
    Restriction restriction = new Restriction(
        resource.restriction().unavailableDistricts(),
        resource.restriction().forbiddenDays(),
        resource.restriction().requiresSpecialCertification()
    );
    List<Tag> tags = resource.tags().stream()
        .map(tag -> new Tag(tag.name()))
        .collect(Collectors.toList());

    List<ComponentQuantity> components = resource.components().stream()
        .map(comp -> new ComponentQuantity(comp.componentId(), comp.quantity()))
        .collect(Collectors.toList());

    return new UpdateServiceCommand(
        serviceId,
        resource.name(),
        resource.description(),
        resource.basePrice(),
        resource.estimatedTime(),
        resource.category(),
        resource.isVisible(),
        resource.createdBy(),
        policy,
        restriction,
        tags,
        components
    );
  }

  /**
   * Converts a {@link ServiceEntity} to a {@link CreateServiceResource}.
   *
   * @param service The entity to convert.
   * @return The converted {@link CreateServiceResource}.
   */
  public static CreateServiceResource toResource(ServiceEntity service) {
    var policy = service.getPolicy();
    var policyResource = policy != null
        ? new PolicyResource(policy.getCancellationPolicy(), policy.getTermsAndConditions())
        : new PolicyResource("", "");

    var restriction = service.getRestriction();
    var restrictionResource = restriction != null
        ? new RestrictionResource(
        restriction.getUnavailableDistricts(),
        restriction.getForbiddenDays(),
        restriction.isRequiresSpecialCertification()
    )
        : new RestrictionResource(List.of(), List.of(), false);

    var tagResources = service.getTags() != null
        ? service.getTags().stream().map(tag -> new TagResource(tag.getName())).toList()
        : Collections.emptyList();

    var componentResources = service.getComponents() != null
        ? service.getComponents().stream().map(
            c -> new ComponentQuantityResource(c.getComponentId(),
        c.getQuantity())).toList()
        : Collections.emptyList();

    return new CreateServiceResource(
        service.getName(),
        service.getDescription(),
        service.getBasePrice(),
        service.getEstimatedTime(),
        service.getCategory(),
        service.isVisible(),
        service.getCreatedBy(),
        policyResource,
        restrictionResource,
        (List<TagResource>) tagResources,
        (List<ComponentQuantityResource>) componentResources
    );
  }

}