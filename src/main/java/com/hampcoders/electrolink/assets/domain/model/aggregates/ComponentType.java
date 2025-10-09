package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdateComponentTypeCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Represents a type or category of component in the system.
 */
@Entity
@Table(name = "component_types")
@Getter
public class ComponentType extends AuditableAbstractAggregateRootNoId<ComponentType> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  /**
   * Protected default constructor for JPA.
   */
  protected ComponentType() {

  }

  /**
   * Constructs a ComponentType from a creation command.
   *
   * @param command The command containing the name and description.
   */
  public ComponentType(final CreateComponentTypeCommand command) {
    this();
    this.name = command.name();
    this.description = command.description();
  }

  /**
   * Updates the name and description of the component type.
   *
   * @param name The new name.
   * @param description The new description.
   */
  public void update(final String name, final String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Updates only the name of the component type from an update command.
   *
   * @param command The command containing the new name.
   */
  public void updateName(final UpdateComponentTypeCommand command) {
    this.name = command.name();
  }

}