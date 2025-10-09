package com.hampcoders.electrolink.monitoring.domain.model.commands;

import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ReportType;

/**
 * Command to create a new report for a service operation.
 *
 * @param serviceOperationId The ID of the service operation the report is associated with.
 * @param reportType The type or category of the report.
 * @param description The detailed description of the incident or observation.
 */
public record AddReportCommand(
    Long serviceOperationId, ReportType reportType, String description) {

}