package com.hampcoders.electrolink.iam.domain.services;

import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.domain.model.queries.GetAllRolesQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetRoleByNameQuery;
import java.util.List;
import java.util.Optional;

/**
 * Domain service interface for handling role-related queries.
 */
public interface RoleQueryService {
  /**
   * Handles the query to retrieve all roles.
   *
   * @param query The {@link GetAllRolesQuery} instance.
   * @return A {@link List} of {@link Role} instances.
   */
  List<Role> handle(GetAllRolesQuery query);

  /**
   * Handles the query to retrieve a role by name.
   *
   * @param query The {@link GetRoleByNameQuery} instance.
   * @return An {@link Optional} containing the {@link Role} if found.
   */
  Optional<Role> handle(GetRoleByNameQuery query);
}