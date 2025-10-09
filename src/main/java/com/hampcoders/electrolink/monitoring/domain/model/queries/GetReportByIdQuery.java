package com.hampcoders.electrolink.monitoring.domain.model.queries;

/**
 * Query to retrieve a specific report by its ID.
 *
 * @param reportId The unique ID of the report to retrieve.
 */
public record GetReportByIdQuery(Long reportId) {}