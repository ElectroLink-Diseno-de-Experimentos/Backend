package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent a retrieved ReportPhoto entity for API consumption.
 *
 * @param id The unique ID of the report photo.
 * @param reportId The ID of the report the photo belongs to.
 * @param url The URL where the photo is stored.
 */
public record ReportPhotoResource(
                                   Long id,
                                   Long reportId,
                                   String url
) {}