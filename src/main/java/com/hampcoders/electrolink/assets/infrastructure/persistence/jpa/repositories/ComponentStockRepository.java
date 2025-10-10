package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.entities.ComponentStock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing ComponentStock entities within a technician's inventory.
 */
@Repository
public interface ComponentStockRepository extends JpaRepository<ComponentStock, UUID> {

  /**
   * Finds a specific ComponentStock item by the containing inventory ID and the component's UID.
   *
   * @param technicianInventoryId The ID of the technician's inventory.
   * @param componentUid The unique ID of the component.
   * @return An Optional containing the ComponentStock, or empty if not found.
   */
  @Query("SELECT cs FROM ComponentStock cs "
      + "WHERE cs.technicianInventory.id = :technicianInventoryId "
      + "AND cs.component.componentUid = :componentUid")
  Optional<ComponentStock> findByTechnicianInventoryIdAndComponentUid(
      @Param("technicianInventoryId") Long technicianInventoryId,
      @Param("componentUid") Long componentUid);

  /**
   * Finds all stock items for a given technician inventory ID.
   * Corrected method name to follow standard Java/JPA naming convention.
   *
   * @param technicianInventoryId The UUID of the technician's inventory.
   * @return A list of component stocks.
   */
  List<ComponentStock> findAllByTechnicianInventoryId(UUID technicianInventoryId);

  /**
   * Finds all stock items for a specific component UID across all inventories.
   * Corrected method name to follow standard Java/JPA naming convention.
   *
   * @param componentUid The unique ID of the component.
   * @return A list of component stocks.
   */
  List<ComponentStock> findAllByComponentComponentUid(Long componentUid);

  /**
   * Finds all stock items where the available quantity is less than the specified value.
   *
   * @param quantity The maximum quantity available.
   * @return A list of component stocks.
   */
  List<ComponentStock> findAllByQuantityAvailableLessThan(int quantity);

  /**
   * Finds all stock items where the quantity available is less than the alert threshold.
   *
   * @return A list of component stocks below the threshold.
   */
  @Query("SELECT cs FROM ComponentStock cs WHERE cs.quantityAvailable < cs.alertThreshold")
  List<ComponentStock> findStockBelowAlertThreshold();
}