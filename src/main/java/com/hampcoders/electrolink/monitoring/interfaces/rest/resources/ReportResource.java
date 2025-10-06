package com.hampcoders.electrolink.monitoring.interfaces.rest.resources;

import java.util.UUID;

public record ReportResource(
    Long id,
    Long requestId,
    String description,
    String reportType
) {}
