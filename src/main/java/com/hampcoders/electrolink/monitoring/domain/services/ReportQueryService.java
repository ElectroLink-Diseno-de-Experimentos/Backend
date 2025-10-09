package com.hampcoders.electrolink.monitoring.domain.services;

import com.hampcoders.electrolink.monitoring.domain.model.aggregates.Report;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllReportsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportsByServiceOperationIdQuery;
import java.util.List;
import java.util.Optional;

/**
 * Defines the query operations available for retrieving Report entities.
 */
public interface ReportQueryService {

  /**
   * Retrieves all existing reports.
   *
   * @param query The query object (marker).
   * @return A list of all reports.
   */
  List<Report> handle(GetAllReportsQuery query);

  /**
   * Retrieves a specific report by its ID.
   *
   * @param query The query object containing the report ID.
   * @return An Optional containing the report, or empty if not found.
   */
  Optional<Report> handle(GetReportByIdQuery query);

  /**
   * Retrieves all reports associated with a specific request ID.
   *
   * @param query The query object containing the request ID.
   * @return A list of reports for the specified request.
   */
  List<Report> handle(GetReportsByServiceOperationIdQuery query);
}