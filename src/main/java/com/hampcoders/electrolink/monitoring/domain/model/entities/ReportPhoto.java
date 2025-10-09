package com.hampcoders.electrolink.monitoring.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Represents a photo associated with a specific Report.
 * This is an entity owned by the Report Aggregate.
 */
@Entity
@Table
public class ReportPhoto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  @Getter
  private Long id;

  @Getter
  @Column(nullable = false)
  private Long reportId;

  @Getter
  @Column(nullable = false)
  private String url;

  public ReportPhoto(Long reportId, String url) {
    this.reportId = reportId;
    this.url = url;
  }

  protected ReportPhoto() {
    // Required by JPA
  }
}
