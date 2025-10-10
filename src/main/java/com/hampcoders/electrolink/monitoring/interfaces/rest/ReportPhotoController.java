package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.domain.services.ReportPhotoCommandService;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportPhotoResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateReportPhotoCommandFromResourceAssembler;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link ReportPhoto} entities.
 * Provides endpoints for adding photos to reports.
 */
@RestController
@RequestMapping("/api/v1/photos")
public class ReportPhotoController {

  private final ReportPhotoCommandService photoCommandService;

  /**
   * Constructs a ReportPhotoController with the necessary command service.
   *
   * @param photoCommandService The service for handling photo commands.
   */
  public ReportPhotoController(ReportPhotoCommandService photoCommandService) {
    this.photoCommandService = photoCommandService;
  }

  /**
   * Endpoint to add a new photo to a report.
   *
   * @param resource The resource containing the report ID and photo URL.
   * @return A ResponseEntity with HTTP status 201 (Created) and the location of the new resource.
   */
  @PostMapping
  public ResponseEntity<?> addPhoto(@RequestBody CreateReportPhotoResource resource) {
    var command = CreateReportPhotoCommandFromResourceAssembler.toCommandFromResource(resource);
    Long photoId = photoCommandService.handle(command);
    return ResponseEntity.created(URI.create("/api/v1/photos/" + photoId)).body(resource);
  }
}