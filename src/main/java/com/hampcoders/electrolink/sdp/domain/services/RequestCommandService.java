package com.hampcoders.electrolink.sdp.domain.services;

import com.hampcoders.electrolink.sdp.domain.model.aggregates.Request;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteRequestCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateRequestCommand;

/**
 * Service interface for handling commands related to requests.
 */
public interface RequestCommandService {
  /**
   * Handles the CreateRequestCommand to create a new request.
   *
   * @param command The command containing the data for the new request.
   *
   * @return The created Request aggregate.
   *
   */
  Request handle(CreateRequestCommand command);

  /**
   * Handles the UpdateRequestCommand to update an existing request.
   *
   * @param command The command containing the updated data for the request.
   *
   * @return The updated Request aggregate.
   *
   */
  Request handle(UpdateRequestCommand command);

  /**
   * Handles the DeleteRequestCommand to delete a request.
   *
   * @param command The command containing the ID of the request to delete.
   */
  void handle(DeleteRequestCommand command);
}
