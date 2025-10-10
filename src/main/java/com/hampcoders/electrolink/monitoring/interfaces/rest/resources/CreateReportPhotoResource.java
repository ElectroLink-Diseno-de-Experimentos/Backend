package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Resource used to represent the data required to associate a new photo with a report.
 *
 * @param reportId The ID of the report the photo belongs to.
 * @param url The URL where the photo is stored.
 */
public record CreateReportPhotoResource(
                                         @NotNull Long reportId,
                                         @NotBlank String url
) {}