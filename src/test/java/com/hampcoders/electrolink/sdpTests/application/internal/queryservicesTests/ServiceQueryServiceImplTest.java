package com.hampcoders.electrolink.sdpTests.application.internal.queryservicesTests;

import com.hampcoders.electrolink.sdp.application.internal.queryservices.ServiceQueryServiceImpl;
import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindServiceByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.GetAllServicesQuery;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceQueryServiceImplTest {

  @Mock
  private ServiceRepository serviceRepository;

  @InjectMocks
  private ServiceQueryServiceImpl service;

  // ========== Tests for FindServiceByIdQuery ==========

  @Test
  @DisplayName("handle(FindServiceByIdQuery) should return Optional with Service when it exists")
  void handle_FindById_WhenServiceExists_ShouldReturnOptionalWithService(){
    // Arrange
    Long serviceId = 100L;
    var query = new FindServiceByIdQuery(serviceId);
    ServiceEntity expected = mock(ServiceEntity.class);
    when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(expected));

    // Act
    Optional<ServiceEntity> actual = service.handle(query);

    // Assert
    assertTrue(actual.isPresent(), "Optional should contain a Service");
    assertSame(expected, actual.get(), "Should return the same Service instance");
    verify(serviceRepository, times(1)).findById(serviceId);
    verifyNoMoreInteractions(serviceRepository);
  }

  @Test
  @DisplayName("handle(FindServiceByIdQuery) should return empty Optional when Service does not exist")
  void handle_FindById_WhenServiceDoesNotExist_ShouldReturnEmptyOptional(){
    // Arrange
    Long serviceId = 999L;
    var query = new FindServiceByIdQuery(serviceId);
    when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

    // Act
    Optional<ServiceEntity> actual = service.handle(query);

    // Assert
    assertFalse(actual.isPresent(), "Optional should be empty when Service does not exist");
    verify(serviceRepository, times(1)).findById(serviceId);
    verifyNoMoreInteractions(serviceRepository);
  }

  // ========== Tests for GetAllServicesQuery ==========

  @Test
  @DisplayName("handle(GetAllServicesQuery) should return List with multiple Services when they exist")
  void handle_GetAll_WhenMultipleServicesExist_ShouldReturnList() {
    // Arrange
    ServiceEntity serviceA = mock(ServiceEntity.class);
    ServiceEntity serviceB = mock(ServiceEntity.class);
    ServiceEntity serviceC = mock(ServiceEntity.class);
    when(serviceRepository.findAll()).thenReturn(List.of(serviceA, serviceB, serviceC));
    var query = new GetAllServicesQuery();

    // Act
    List<ServiceEntity> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(3, actual.size(), "Should return 3 services");
    assertSame(serviceA, actual.get(0), "First service should match");
    assertSame(serviceB, actual.get(1), "Second service should match");
    assertSame(serviceC, actual.get(2), "Third service should match");
    verify(serviceRepository, times(1)).findAll();
    verifyNoMoreInteractions(serviceRepository);
  }

  @Test
  @DisplayName("handle(GetAllServicesQuery) should return empty List when no Services exist")
  void handle_GetAll_WhenNoServicesExist_ShouldReturnEmptyList() {
    // Arrange
    when(serviceRepository.findAll()).thenReturn(Collections.emptyList());
    var query = new GetAllServicesQuery();

    // Act
    List<ServiceEntity> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertTrue(actual.isEmpty(), "List should be empty when no services exist");
    assertEquals(0, actual.size(), "Size should be 0");
    verify(serviceRepository, times(1)).findAll();
    verifyNoMoreInteractions(serviceRepository);
  }

  @Test
  @DisplayName("handle(GetAllServicesQuery) should return List with single Service when only one exists")
  void handle_GetAll_WhenSingleServiceExists_ShouldReturnListWithOne() {
    // Arrange
    ServiceEntity serviceEntity = mock(ServiceEntity.class);
    when(serviceRepository.findAll()).thenReturn(List.of(serviceEntity));
    var query = new GetAllServicesQuery();

    // Act
    List<ServiceEntity> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(1, actual.size(), "Should return exactly 1 service");
    assertSame(serviceEntity, actual.get(0), "Service should match");
    verify(serviceRepository, times(1)).findAll();
    verifyNoMoreInteractions(serviceRepository);
  }
}
