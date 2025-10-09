package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.AddReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteReportCommand;

/**
 * Defines the commands available for managing Report and related entities.
 */
public interface ReportCommandService {

  /**
   * Handles the creation of a new report.
   *
   * @param command The command containing the report details.
   * @return The ID of the newly created report.
   */
  Long handle(AddReportCommand command);

  /**
   * Handles the deletion of a specific report.
   *
   * @param command The command containing the ID of the report to delete.
   */
  void handle(DeleteReportCommand command);

  /**
   * Handles the addition of a photo to a report.
   *
   * @param command The command containing the photo details.
   * @return The ID of the newly created report photo.
   */
  Long handle(AddPhotoCommand command);
}