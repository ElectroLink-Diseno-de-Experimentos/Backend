package com.hampcoders.electrolink.assets.domain.model.aggregates;

import com.hampcoders.electrolink.assets.domain.model.commands.CreatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.commands.UpdatePropertyCommand;
import com.hampcoders.electrolink.assets.domain.model.entities.PropertyStatus;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.Address;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.District;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.Region;
import com.hampcoders.electrolink.shared.domain.model.aggregates.AuditableAbstractAggregateRootNoId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

/**
 * Represents a property aggregate root.
 */
@Entity
@Table(name = "properties")
@Getter
public class Property extends AuditableAbstractAggregateRootNoId<Property> {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Embedded
  private OwnerId ownerId;

  @Embedded
  private Address address;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "name", column = @Column(name = "region_name"))
  })
  private Region region;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "name", column = @Column(name = "district_name"))
  })
  private District district;

  /*@ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id", nullable = false)
  private PropertyStatus status;*/

  /**
   * Protected default constructor for JPA.
   */
  protected Property() {
  }

  /**
   * Constructs a Property from a creation command.
   *
   * @param command The command containing property details.
   */
  public Property(final CreatePropertyCommand command) {
    this.ownerId = command.ownerId();
    this.address = command.address();
    this.region = command.region();
    this.district = command.district();
    // this.status = PropertyStatus.getDefaultPropertyStatus();
  }

  /**
   * Updates the mutable fields of the property based on an update command.
   *
   * @param command The command containing the new property details.
   */
  public void update(final UpdatePropertyCommand command) {
    this.address = command.address();
    this.region = command.region();
    this.district = command.district();
  }

  /*public void updateAddress(Address newAddress) {
      if (this.status.getName() == PropertyStatuses.INACTIVE) {
          throw new IllegalStateException("Cannot update the address of an inactive property.");
      }
      this.address = newAddress;
  }

  public void deactivate() {
      if (this.status.getName() == PropertyStatuses.INACTIVE) {
          return;
      }
      this.status = PropertyStatus.toPropertyStatusFromName("INACTIVE");
  }*/
}