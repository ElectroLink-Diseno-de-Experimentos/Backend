package com.hampcoders.electrolink.sdp.domain.model.valueobjects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents restrictions for a service or technician.
 * It is an embeddable value object.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Restriction {
  @ElementCollection
  private List<String> unavailableDistricts = new ArrayList<>();

  @ElementCollection
  private List<String> forbiddenDays = new ArrayList<>();

  private boolean requiresSpecialCertification;

  /**
   * Constructor for Restriction.
   *
   * @param unavailableDistricts List of unavailable districts.
   * @param forbiddenDays List of forbidden days.
   * @param requiresSpecialCertification Whether special certification is required.
   */
  public Restriction(List<String> unavailableDistricts,
                     List<String> forbiddenDays,
                     boolean requiresSpecialCertification) {
    this.unavailableDistricts = unavailableDistricts;
    this.forbiddenDays = forbiddenDays;
    this.requiresSpecialCertification = requiresSpecialCertification;
  }
}
