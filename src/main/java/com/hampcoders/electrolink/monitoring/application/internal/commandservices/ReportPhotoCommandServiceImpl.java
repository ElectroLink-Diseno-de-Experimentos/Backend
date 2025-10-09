package com.hampcoders.electrolink.monitoring.application.internal.commandservices;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.entities.ReportPhoto;
import com.hampcoders.electrolink.monitoring.domain.services.ReportPhotoCommandService;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportPhotoRepository;
import com.hampcoders.electrolink.monitoring.infrastructure.persistence.jpa.repositories.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementation of the command service for ReportPhoto entities.
 */
@Service
public class ReportPhotoCommandServiceImpl implements ReportPhotoCommandService {

  private final ReportRepository reportRepository;
  private final ReportPhotoRepository reportPhotoRepository;

  public ReportPhotoCommandServiceImpl(ReportRepository reportRepository,
                                       ReportPhotoRepository reportPhotoRepository) {
    this.reportRepository = reportRepository;
    this.reportPhotoRepository = reportPhotoRepository;
  }

  /**
   * Handles the command to add a new photo to an existing report.
   *
   * @param command The command containing the report ID and photo URL.
   * @return The ID of the created report photo.
   */
  @Transactional
  @Override
  public Long handle(AddPhotoCommand command) {
    reportRepository.findById(command.reportId())
        .orElseThrow(() -> new IllegalArgumentException(
            "Report not found with ID: " + command.reportId()));

    ReportPhoto photo = new ReportPhoto(command.reportId(), command.url());
    reportPhotoRepository.save(photo);
    return photo.getId();
  }
}