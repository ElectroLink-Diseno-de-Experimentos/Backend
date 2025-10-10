package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportResource;

/**
 * Assembler responsible for converting {@link Report} entities
 * into {@link ReportResource} objects.
 */
public class ReportResourceFromEntityAssembler {

  /**
   * Converts a Report entity into a ReportResource.
   *
   * @param entity The Report entity.
   * @return The corresponding ReportResource.
   */
  public static ReportResource toResourceFromEntity(Report entity) {
    return new ReportResource(
        entity.getId(),
        entity.getServiceOperationId(),
        entity.getDescription(),
        entity.getReportType().name()
    );
  }
}