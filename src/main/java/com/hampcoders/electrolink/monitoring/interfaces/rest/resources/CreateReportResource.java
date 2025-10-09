package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

/**
 * Resource used to represent the data required to create a new report
 * associated with a service operation request.
 *
 * @param serviceOperationId The ID of the service operation request the report relates to.
 * @param reportType The type of the report (e.g., INCIDENT, COMPLETION).
 * @param description The textual description or content of the report.
 */
public record CreateReportResource(
                                    Long serviceOperationId,
                                    String reportType,
                                    String description
) {}