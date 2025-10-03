package com.hampcoders.electrolink.assets.infrastructure.persistence.jpa.repositories;

import com.hampcoders.electrolink.assets.domain.model.aggregates.Component;
import com.hampcoders.electrolink.assets.domain.model.valueobjects.ComponentTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {
    boolean existsByComponentTypeId(ComponentTypeId componentTypeId);
    List<Component> findByComponentTypeId(Long typeId);
    Optional<Component> findByComponentUid(Long componentUid);
    boolean existsByName(String name);
    List<Component> findByComponentUidIn(List<Long> uuids);
    List<Component> findTop10ByNameContainingIgnoreCase(String nameFragment);
}