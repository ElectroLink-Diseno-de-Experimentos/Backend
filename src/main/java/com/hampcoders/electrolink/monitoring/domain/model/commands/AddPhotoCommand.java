package com.hampcoders.electrolink.monitoring.domain.model.commands;

/**
 * Command to add a new photo to an existing report.
 *
 * @param reportId The ID of the report the photo belongs to.
 * @param url The URL where the photo is stored.
 */
public record AddPhotoCommand(Long reportId, String url) {

}