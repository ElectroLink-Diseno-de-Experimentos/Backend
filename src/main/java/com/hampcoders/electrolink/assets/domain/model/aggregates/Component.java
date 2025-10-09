package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreateComponentCommand;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.UUID;
import lombok.Getter;

/**
 * Represents a specific component used in the system, managed as an aggregate root.
 */
@Entity
@Table(name = "components")
@Getter
public class Component extends AuditableAbstractAggregateRootNoId<Component> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "component_uid", unique = true, nullable = false)
  private Long componentUid;

  @Getter
  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Getter
  @Column(name = "description", length = 255, nullable = true)
  private String description;

  @Getter
  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "component_type_id")
  private Long componentTypeId;

  @Version
  @Column(name = "version")
  private Long version;

  /**
   * Protected default constructor for JPA and internal initialization.
   */
  protected Component() {
    this.name = "";
    this.description = "";
    this.componentTypeId = null;
    this.isActive = false;
  }

  /**
   * Constructs a Component from a creation command.
   *
   * @param command The command containing the component details.
   */
  public Component(final CreateComponentCommand command) {
    this();
    this.name = command.name();
    this.description = command.description();
    this.componentTypeId = command.componentTypeId();
    this.isActive = true;
  }

  /**
   * Updates the name and description of the component.
   *
   * @param name The new name.
   * @param description The new description.
   */
  public void updateInfo(final String name, final String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * Deactivates the component, setting its active status to false.
   */
  public void deactivate() {
    this.isActive = false;
  }
}