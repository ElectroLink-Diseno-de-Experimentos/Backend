package com.hampcoders.electrolink.sdp.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a policy with cancellation and terms and conditions.
 * It is an embeddable value object.
 */
@Embeddable
@Getter
@NoArgsConstructor
public class Policy {
  private String cancellationPolicy;
  private String termsAndConditions;

  /**
   * Constructor for Policy.
   *
   * @param cancellationPolicy The cancellation policy.
   * @param termsAndConditions The terms and conditions.
   */
  public Policy(String cancellationPolicy, String termsAndConditions) {
    this.cancellationPolicy = cancellationPolicy;
    this.termsAndConditions = termsAndConditions;
  }
}
