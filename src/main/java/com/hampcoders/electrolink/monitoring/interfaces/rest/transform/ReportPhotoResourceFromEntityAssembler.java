package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportPhotoResource;

/**
 * Assembler responsible for converting {@link ReportPhoto} entities
 * into {@link ReportPhotoResource} objects.
 */
public class ReportPhotoResourceFromEntityAssembler {

  /**
   * Converts a ReportPhoto entity into a ReportPhotoResource.
   *
   * @param entity The ReportPhoto entity.
   * @return The corresponding ReportPhotoResource.
   */
  public static ReportPhotoResource toResourceFromEntity(ReportPhoto entity) {
    return new ReportPhotoResource(
        entity.getId(),
        entity.getReportId(),
        entity.getUrl()
    );
  }
}