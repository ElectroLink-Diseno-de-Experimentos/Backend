package com.hampcoders.electrolink.sdp.domain.model.entities;

import com.hampcoders.electrolink.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a bill.
 * It is an embeddable entity, meaning it will be part of another entity.
 */
@Getter
@NoArgsConstructor
@Embeddable
public class Bill extends AuditableModel {

  private String billingPeriod;
  private double energyConsumed;
  private double amountPaid;
  private String billImageUrl;

  /**
   * Constructor for Bill.
   *
   * @param billingPeriod The billing period.
   *
   * @param energyConsumed The energy consumed.
   *
   * @param amountPaid The amount paid.
   *
   * @param billImageUrl The URL of the bill image.
   *
   */
  public Bill(String billingPeriod, double energyConsumed,
              double amountPaid, String billImageUrl) {
    this.billingPeriod = billingPeriod;
    this.energyConsumed = energyConsumed;
    this.amountPaid = amountPaid;
    this.billImageUrl = billImageUrl;
  }
}
