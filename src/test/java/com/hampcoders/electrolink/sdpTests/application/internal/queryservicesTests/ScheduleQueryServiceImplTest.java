package com.hampcoders.electrolink.sdpTests.application.internal.queryservicesTests;

import com.hampcoders.electrolink.sdp.application.internal.queryservices.ScheduleQueryServiceImpl;
import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindScheduleByIdQuery;
import com.hampcoders.electrolink.sdp.domain.model.queries.FindSchedulesByTechnicianIdQuery;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ScheduleRepository;
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
public class ScheduleQueryServiceImplTest {

  @Mock
  private ScheduleRepository scheduleRepository;

  @InjectMocks
  private ScheduleQueryServiceImpl service;

  // ========== Tests for FindScheduleByIdQuery ==========

  @Test
  @DisplayName("handle(FindScheduleByIdQuery) should return Optional with Schedule when it exists")
  void handle_FindById_WhenScheduleExists_ShouldReturnOptionalWithSchedule(){
    // Arrange
    Long scheduleId = 1L;
    var query = new FindScheduleByIdQuery(scheduleId);
    ScheduleAggregate expected = mock(ScheduleAggregate.class);
    when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(expected));

    // Act
    Optional<ScheduleAggregate> actual = service.handle(query);

    // Assert
    assertTrue(actual.isPresent(), "Optional should contain a Schedule");
    assertSame(expected, actual.get(), "Should return the same Schedule instance");
    verify(scheduleRepository, times(1)).findById(scheduleId);
    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(FindScheduleByIdQuery) should return empty Optional when Schedule does not exist")
  void handle_FindById_WhenScheduleDoesNotExist_ShouldReturnEmptyOptional(){
    // Arrange
    Long scheduleId = 999L;
    var query = new FindScheduleByIdQuery(scheduleId);
    when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

    // Act
    Optional<ScheduleAggregate> actual = service.handle(query);

    // Assert
    assertFalse(actual.isPresent(), "Optional should be empty when Schedule does not exist");
    verify(scheduleRepository, times(1)).findById(scheduleId);
    verifyNoMoreInteractions(scheduleRepository);
  }

  // ========== Tests for FindSchedulesByTechnicianIdQuery ==========

  @Test
  @DisplayName("handle(FindSchedulesByTechnicianIdQuery) should return List with multiple Schedules when they exist")
  void handle_FindByTechnicianId_WhenMultipleSchedulesExist_ShouldReturnList() {
    // Arrange
    String technicianId = "TECH-001";
    ScheduleAggregate scheduleA = mock(ScheduleAggregate.class);
    ScheduleAggregate scheduleB = mock(ScheduleAggregate.class);
    ScheduleAggregate scheduleC = mock(ScheduleAggregate.class);
    when(scheduleRepository.findByTechnicianId(technicianId))
        .thenReturn(List.of(scheduleA, scheduleB, scheduleC));
    var query = new FindSchedulesByTechnicianIdQuery(technicianId);

    // Act
    List<ScheduleAggregate> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(3, actual.size(), "Should return 3 schedules");
    assertSame(scheduleA, actual.get(0), "First schedule should match");
    assertSame(scheduleB, actual.get(1), "Second schedule should match");
    assertSame(scheduleC, actual.get(2), "Third schedule should match");
    verify(scheduleRepository, times(1)).findByTechnicianId(technicianId);
    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(FindSchedulesByTechnicianIdQuery) should return empty List when no Schedules exist for technician")
  void handle_FindByTechnicianId_WhenNoSchedulesExist_ShouldReturnEmptyList() {
    // Arrange
    String technicianId = "TECH-999";
    when(scheduleRepository.findByTechnicianId(technicianId)).thenReturn(Collections.emptyList());
    var query = new FindSchedulesByTechnicianIdQuery(technicianId);

    // Act
    List<ScheduleAggregate> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertTrue(actual.isEmpty(), "List should be empty when no schedules exist");
    assertEquals(0, actual.size(), "Size should be 0");
    verify(scheduleRepository, times(1)).findByTechnicianId(technicianId);
    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(FindSchedulesByTechnicianIdQuery) should return List with single Schedule when only one exists")
  void handle_FindByTechnicianId_WhenSingleScheduleExists_ShouldReturnListWithOne() {
    // Arrange
    String technicianId = "TECH-456";
    ScheduleAggregate schedule = mock(ScheduleAggregate.class);
    when(scheduleRepository.findByTechnicianId(technicianId)).thenReturn(List.of(schedule));
    var query = new FindSchedulesByTechnicianIdQuery(technicianId);

    // Act
    List<ScheduleAggregate> actual = service.handle(query);

    // Assert
    assertNotNull(actual, "Result should not be null");
    assertEquals(1, actual.size(), "Should return exactly 1 schedule");
    assertSame(schedule, actual.get(0), "Schedule should match");
    verify(scheduleRepository, times(1)).findByTechnicianId(technicianId);
    verifyNoMoreInteractions(scheduleRepository);
  }
}
