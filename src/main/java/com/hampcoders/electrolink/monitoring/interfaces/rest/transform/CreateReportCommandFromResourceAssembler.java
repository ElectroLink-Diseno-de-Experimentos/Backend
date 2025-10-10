package com.hampcoders.electrolink.monitoring.interfaces.rest.transform;

import com.hampcoders.electrolink.monitoring.domain.model.commands.AddReportCommand;
import com.hampcoders.electrolink.monitoring.domain.model.valueobjects.ReportType;
import com.hampcoders.electrolink.monitoring.interfaces.rest.resources.CreateReportResource;

/**
 * Assembler responsible for converting {@link CreateReportResource} objects
 * into {@link AddReportCommand} objects.
 */
public class CreateReportCommandFromResourceAssembler {

  /**
   * Converts a CreateReportResource into an AddReportCommand.
   *
   * @param resource The resource object containing report creation data.
   * @return The corresponding AddReportCommand.
   */
  public static AddReportCommand toCommandFromResource(CreateReportResource resource) {
    return new AddReportCommand(
        resource.serviceOperationId(),
        ReportType.valueOf(resource.reportType()),
        resource.description()
    );
  }
}