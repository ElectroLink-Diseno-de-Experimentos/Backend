package com.hampcoders.electrolink.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value object representing the identifier of a technician.
 */
@Getter
@Embeddable
public class TechnicianId implements Serializable {

  private Long technicianId;

  public TechnicianId() {}

  public TechnicianId(Long id) {
    this.technicianId = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TechnicianId)) {
      return false;
    }
    TechnicianId that = (TechnicianId) o;
    return Objects.equals(technicianId, that.technicianId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(technicianId);
  }
}
