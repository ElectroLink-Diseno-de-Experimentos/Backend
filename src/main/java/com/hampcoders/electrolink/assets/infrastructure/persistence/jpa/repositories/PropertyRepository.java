package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Property;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.OwnerId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Property entities.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {
  /**
   * Finds all properties associated with a specific owner ID.
   *
   * @param ownerId The owner's ID.
   * @return A list of properties belonging to the owner.
   */
  List<Property> findPropertiesByOwnerId(OwnerId ownerId);

  /**
   * Finds a specific property associated with a single owner ID.
   * Note: This assumes a 1-to-1 or 1-to-many relationship where you expect at most one result,
   * otherwise, use the List return type.
   *
   * @param ownerId The owner's ID.
   * @return An Optional containing the Property, or empty if not found.
   */
  Optional<Property> findPropertyByOwnerId(OwnerId ownerId);
}