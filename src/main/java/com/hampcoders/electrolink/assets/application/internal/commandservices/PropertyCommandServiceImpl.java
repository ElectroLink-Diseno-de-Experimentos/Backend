package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.commands.CreatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeletePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.services.PropertyCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.PropertyRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Implementation of the command service for the Property aggregate.
 */
@Service
public class PropertyCommandServiceImpl implements PropertyCommandService {

  private final PropertyRepository propertyRepository;

  /**
   * Constructs a PropertyCommandServiceImpl.
   *
   * @param propertyRepository The repository for Property entities.
   */
  public PropertyCommandServiceImpl(final PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  @Override
  public UUID handle(final CreatePropertyCommand command) {
    var property = new Property(command);
    propertyRepository.save(property);
    return property.getId();
  }

  @Override
  public Optional<Property> handle(final UpdatePropertyCommand command) {
    return propertyRepository.findById(command.propertyId()).map(property -> {
      property.update(command);
      return propertyRepository.save(property);
    });
  }

  @Override
  public Boolean handle(final DeletePropertyCommand command) {
    return propertyRepository.findById(command.propertyId()).map(property -> {
      // property.deactivate();
      propertyRepository.save(property);
      return true;
    }).orElse(false);
  }
}