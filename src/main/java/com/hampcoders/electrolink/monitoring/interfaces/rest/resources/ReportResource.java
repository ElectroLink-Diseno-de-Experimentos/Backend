package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent a retrieved Report entity for API consumption.
 *
 * @param id The unique ID of the report.
 * @param serviceOperationId The ID of the service operation request the report relates to.
 * @param description The textual description or content of the report.
 * @param reportType The type of the report (e.g., INCIDENT, COMPLETION).
 */
public record ReportResource(
                              Long id,
                              Long serviceOperationId,
                              String description,
                              String reportType
) {}