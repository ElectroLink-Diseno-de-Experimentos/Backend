package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Component entities.
 */
@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
  /**
   * Checks if a component exists with the given component type ID.
   *
   * @param componentTypeId The component type ID value object.
   * @return True if a component exists, false otherwise.
   */
  boolean existsByComponentTypeId(ComponentTypeId componentTypeId);

  /**
   * Finds all components associated with a specific component type ID.
   *
   * @param typeId The ID of the component type.
   * @return A list of components.
   */
  List<Component> findByComponentTypeId(Long typeId);

  /**
   * Finds a component by its unique component UID.
   *
   * @param componentUid The unique ID of the component.
   * @return An Optional containing the Component, or empty if not found.
   */
  Optional<Component> findByComponentUid(Long componentUid);

  /**
   * Checks if a component with the given name already exists.
   *
   * @param name The name of the component.
   * @return True if a component with that name exists, false otherwise.
   */
  boolean existsByName(String name);

  /**
   * Finds components whose UIDs are in the provided list.
   *
   * @param uuids The list of component UIDs.
   * @return A list of components matching the UIDs.
   */
  List<Component> findByComponentUidIn(List<Long> uuids);

  /**
   * Finds up to 10 components whose name contains the specified fragment, ignoring case.
   *
   * @param nameFragment The fragment of the name to search for.
   * @return A list of matching components.
   */
  List<Component> findTop10ByNameContainingIgnoreCase(String nameFragment);
}