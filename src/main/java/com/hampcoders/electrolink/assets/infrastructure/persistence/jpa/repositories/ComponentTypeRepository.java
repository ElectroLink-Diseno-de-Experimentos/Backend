package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.ComponentType;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing ComponentType entities.
 */
@Repository
public interface ComponentTypeRepository extends JpaRepository<ComponentType, Long> {

  /**
   * Checks if a component type with the given name already exists.
   *
   * @param name The name of the component type.
   * @return True if a component type with that name exists, false otherwise.
   */
  boolean existsByName(String name);
}