package com.hampcoders.electrolink.monitoring.interfaces.rest;

import com.hampcoders.electrolink.monitoring.application.internal.commandservices.ReportCommandServiceImpl;
import com.hampcoders.electrolink.monitoring.application.internal.queryservices.ReportQueryServiceImpl;
import com.hampcoders.electrolink.monitoring.domain.model.commands.DeleteReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetAllReportsQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportByIdQuery;
import com.hampcoders.electrolink.monitoring.domain.model.queries.GetReportsByServiceOperationIdQuery;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.ReportResource;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.hampcoders.electrolink.monitoring.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for managing reports, providing endpoints for creation,
 * retrieval, and deletion of report entities.
 */
@RestController
@RequestMapping("/api/v1/reports")
public class ReportsController {

  private final ReportCommandServiceImpl commandService;
  private final ReportQueryServiceImpl queryService;

  /**
   * Constructs a ReportsController with the necessary command and query services.
   *
   * @param commandService The service for handling report commands (CUD).
   * @param queryService The service for handling report queries (R).
   */
  public ReportsController(ReportCommandServiceImpl commandService,
                           ReportQueryServiceImpl queryService) {
    this.commandService = commandService;
    this.queryService = queryService;
  }

  /**
   * Creates a new report in the system.
   *
   * @param resource The data for the new report.
   * @return The ID of the newly created report with HTTP status 201 (Created).
   */
  @Operation(summary = "Create a report")
  @PostMapping
  public ResponseEntity<Long> createReport(@RequestBody CreateReportResource resource) {
    var command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);
    var reportId = commandService.handle(command);
    return new ResponseEntity<>(reportId, HttpStatus.CREATED);
  }

  /**
   * Deletes a report by its unique ID.
   *
   * @param reportId The ID of the report to delete.
   * @return Empty response with HTTP status 204 (No Content).
   */
  @Operation(summary = "Delete a report")
  @DeleteMapping("/{reportId}")
  public ResponseEntity<Void> deleteReport(
      @Parameter(description = "Id of the report") @PathVariable Long reportId
  ) {
    var command = new DeleteReportCommand(reportId);
    commandService.handle(command);
    return ResponseEntity.noContent().build();
  }

  /**
   * Retrieves a specific report by its unique ID.
   *
   * @param reportId The ID of the report to retrieve.
   * @return The requested report resource with HTTP status 200 (OK).
   */
  @Operation(summary = "Get report by ID")
  @GetMapping("/{reportId}")
  public ResponseEntity<ReportResource> getReportById(
      @Parameter(description = "Id of the report") @PathVariable Long reportId
  ) {
    var query = new GetReportByIdQuery(reportId);
    var report = queryService.handle(query)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));

    return ResponseEntity.ok(ReportResourceFromEntityAssembler.toResourceFromEntity(report));
  }

  /**
   * Retrieves all reports available in the system.
   *
   * @return A list of all report resources with HTTP status 200 (OK).
   */
  @Operation(summary = "Get all reports")
  @GetMapping
  public ResponseEntity<List<ReportResource>> getAllReports() {
    var reports = queryService.handle(new GetAllReportsQuery());
    var resources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }

  /**
   * Retrieves all reports associated with a specific service operation request ID.
   *
   * @param serviceOperationId The ID of the service operation request.
   * @return A list of report resources for the request with HTTP status 200 (OK).
   */
  @Operation(summary = "Get reports by request ID")
  @GetMapping("/requests/{serviceOperationId}")
  public ResponseEntity<List<ReportResource>> getReportsByServiceOperationId(
      @Parameter(description = "Request ID") @PathVariable Long serviceOperationId
  ) {
    var query = new GetReportsByServiceOperationIdQuery(serviceOperationId);
    var reports = queryService.handle(query);
    var resources = reports.stream()
        .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(resources);
  }
}