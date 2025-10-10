package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddPhotoCommand;

/**
 * Defines the commands available for managing ReportPhoto entities.
 */
public interface ReportPhotoCommandService {

  /**
   * Handles the command to add a new photo to an existing report.
   *
   * @param command The command containing the photo details.
   * @return The ID of the newly created report photo.
   */
  Long handle(AddPhotoCommand command);
}