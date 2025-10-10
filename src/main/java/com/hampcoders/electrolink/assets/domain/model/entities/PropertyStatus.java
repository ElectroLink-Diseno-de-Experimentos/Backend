package com.hampcoders.electrolink.assets.domain.model.entities;

import com.hampcoders.electrolink.assets.domain.model.valueobjects.PropertyStatuses;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

/**
 * Represents the status of a property, mapped to the PropertyStatuses enumeration.
 */
@Entity
@Table(name = "property_statuses")
@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false, unique = true)
  private PropertyStatuses name;

  /**
   * Constructs a PropertyStatus with a specified status name.
   *
   * @param name The status enumeration value.
   */
  public PropertyStatus(final PropertyStatuses name) {
    this.name = name;
  }

  /**
   * Gets the string representation of the property status name.
   *
   * @return The status name as a String.
   */
  public String getStringName() {
    return name.name();
  }

  /**
   * Retrieves the default property status.
   *
   * @return A new PropertyStatus instance set to DEFAULT.
   */
  public static PropertyStatus getDefaultPropertyStatus() {
    return new PropertyStatus(PropertyStatuses.DEFAULT);
  }

  /**
   * Converts a string name to a PropertyStatus entity.
   *
   * @param name The string representation of the status name.
   * @return A new PropertyStatus instance.
   */
  public static PropertyStatus toPropertyStatusFromName(final String name) {
    return new PropertyStatus(PropertyStatuses.valueOf(name));
  }

}