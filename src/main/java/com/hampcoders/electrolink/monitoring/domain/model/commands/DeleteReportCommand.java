package com.hampcoders.electrolink.monitoring.domain.model.commands;

/**
 * Command to request the deletion of an existing report.
 *
 * @param reportId The ID of the report to be deleted.
 */
public record DeleteReportCommand(Long reportId) {}