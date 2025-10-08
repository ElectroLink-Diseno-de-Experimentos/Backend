package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.queries.GetAllUsersQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByIdQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByUsernameQuery;
import java.util.List;
import java.util.Optional;

/**
 * Domain service interface for handling user-related queries.
 */
public interface UserQueryService {
  /**
   * Handles the query to retrieve all users.
   *
   * @param query The {@link GetAllUsersQuery} instance.
   * @return A {@link List} of {@link User} instances.
   */
  List<User> handle(GetAllUsersQuery query);

  /**
   * Handles the query to retrieve a user by ID.
   *
   * @param query The {@link GetUserByIdQuery} instance.
   * @return An {@link Optional} containing the {@link User} if found.
   */
  Optional<User> handle(GetUserByIdQuery query);

  /**
   * Handles the query to retrieve a user by username.
   *
   * @param query The {@link GetUserByUsernameQuery} instance.
   * @return An {@link Optional} containing the {@link User} if found.
   */
  Optional<User> handle(GetUserByUsernameQuery query);
}