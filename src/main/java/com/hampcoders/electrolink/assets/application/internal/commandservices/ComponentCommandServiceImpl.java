package com.hampcoders.electrolink.assets.application.internal.commandservices;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.DeleteComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentId;
import com.hampcoders.electrolink.assets.domain.services.ComponentCommandService;
import com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories.ComponentRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Component command service implementation.
 */
@Service
public class ComponentCommandServiceImpl implements ComponentCommandService {

  private final ComponentRepository componentRepository;

  /**
   * Constructs a ComponentCommandServiceImpl.
   *
   * @param componentRepository The component repository.
   */
  public ComponentCommandServiceImpl(final ComponentRepository componentRepository) {
    this.componentRepository = componentRepository;
  }

  @Override
  public ComponentId handle(final CreateComponentCommand command) {
    if (componentRepository.existsByName(command.name())) {
      throw new IllegalStateException("Component with the same name already exists");
    }
    final var component = new Component(command);
    final var savedComponent = componentRepository.save(component);
    return new ComponentId(savedComponent.getComponentUid());
  }

  @Override
  public Optional<Component> handle(final UpdateComponentCommand command) {
    return componentRepository.findById(command.componentId())
        .map(component -> {
          component.updateInfo(command.name(), command.description());
          return componentRepository.save(component);
        });
  }

  @Override
  public Boolean handle(final DeleteComponentCommand command) {
    if (!componentRepository.existsById(command.componentId())) {
      return false;
    }
    componentRepository.deleteById(command.componentId());
    return true;
  }
}