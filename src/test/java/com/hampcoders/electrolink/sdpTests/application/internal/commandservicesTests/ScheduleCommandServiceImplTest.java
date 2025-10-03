package com.hampcoders.electrolink.sdpTests.application.internal.commandservicesTests;

import com.hampcoders.electrolink.sdp.application.internal.commandservices.ScheduleCommandServiceImpl;
import com.hampcoders.electrolink.sdp.domain.model.aggregates.ScheduleAggregate;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteScheduleCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateScheduleCommand;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleCommandServiceImplTest {

  @Mock
  private ScheduleRepository scheduleRepository;

  @InjectMocks
  private ScheduleCommandServiceImpl scheduleCommandService;

  // ========== Tests for CreateScheduleCommand ==========

  @Test
  @DisplayName("handle(CreateScheduleCommand) should create and save a new schedule successfully")
  void handle_CreateSchedule_ShouldSaveAndReturnId() {
    // Arrange
    var command = new CreateScheduleCommand(
        "TECH-001",
        "Monday",
        "09:00",
        "17:00"
    );

    ScheduleAggregate savedSchedule = mock(ScheduleAggregate.class);
    when(savedSchedule.getId()).thenReturn(1L);
    when(scheduleRepository.save(any(ScheduleAggregate.class))).thenReturn(savedSchedule);

    // Act
    Long scheduleId = scheduleCommandService.handle(command);

    // Assert
    assertNotNull(scheduleId, "Schedule ID should not be null");
    assertEquals(1L, scheduleId, "Should return the correct schedule ID");

    ArgumentCaptor<ScheduleAggregate> captor = ArgumentCaptor.forClass(ScheduleAggregate.class);
    verify(scheduleRepository, times(1)).save(captor.capture());

    ScheduleAggregate capturedSchedule = captor.getValue();
    assertEquals("TECH-001", capturedSchedule.getTechnicianId(), "Technician ID should match");
    assertEquals("Monday", capturedSchedule.getDay(), "Day should match");
    assertEquals("09:00", capturedSchedule.getStartTime(), "Start time should match");
    assertEquals("17:00", capturedSchedule.getEndTime(), "End time should match");

    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(CreateScheduleCommand) should create schedule with valid data")
  void handle_CreateSchedule_WithValidData_ShouldCreateCorrectSchedule() {
    // Arrange
    var command = new CreateScheduleCommand(
        "TECH-456",
        "Friday",
        "08:00",
        "16:00"
    );

    ScheduleAggregate savedSchedule = mock(ScheduleAggregate.class);
    when(savedSchedule.getId()).thenReturn(10L);
    when(scheduleRepository.save(any(ScheduleAggregate.class))).thenReturn(savedSchedule);

    // Act
    Long scheduleId = scheduleCommandService.handle(command);

    // Assert
    assertNotNull(scheduleId, "Schedule ID should not be null");
    assertEquals(10L, scheduleId, "Should return ID 10");
    verify(scheduleRepository, times(1)).save(any(ScheduleAggregate.class));
  }

  // ========== Tests for UpdateScheduleCommand ==========

  @Test
  @DisplayName("handle(UpdateScheduleCommand) should update existing schedule successfully")
  void handle_UpdateSchedule_WhenScheduleExists_ShouldUpdateSuccessfully() {
    // Arrange
    Long scheduleId = 1L;
    var command = new UpdateScheduleCommand(
        scheduleId,
        "TECH-001",
        "Tuesday",
        "10:00",
        "18:00"
    );

    ScheduleAggregate existingSchedule = new ScheduleAggregate("TECH-001", "Monday", "09:00", "17:00");
    when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(existingSchedule));
    when(scheduleRepository.save(any(ScheduleAggregate.class))).thenReturn(existingSchedule);

    // Act
    scheduleCommandService.handle(command);

    // Assert
    assertEquals("Tuesday", existingSchedule.getDay(), "Day should be updated");
    assertEquals("10:00", existingSchedule.getStartTime(), "Start time should be updated");
    assertEquals("18:00", existingSchedule.getEndTime(), "End time should be updated");

    verify(scheduleRepository, times(1)).findById(scheduleId);
    verify(scheduleRepository, times(1)).save(existingSchedule);
    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(UpdateScheduleCommand) should throw exception when schedule does not exist")
  void handle_UpdateSchedule_WhenScheduleDoesNotExist_ShouldThrowException() {
    // Arrange
    Long scheduleId = 999L;
    var command = new UpdateScheduleCommand(
        scheduleId,
        "TECH-001",
        "Wednesday",
        "09:00",
        "17:00"
    );

    when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.empty());

    // Act & Assert
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> scheduleCommandService.handle(command),
        "Should throw IllegalArgumentException when schedule not found"
    );

    assertEquals("Schedule not found", exception.getMessage(), "Exception message should match");
    verify(scheduleRepository, times(1)).findById(scheduleId);
    verify(scheduleRepository, never()).save(any());
    verifyNoMoreInteractions(scheduleRepository);
  }

  // ========== Tests for DeleteScheduleCommand ==========

  @Test
  @DisplayName("handle(DeleteScheduleCommand) should delete existing schedule successfully")
  void handle_DeleteSchedule_WhenScheduleExists_ShouldDeleteSuccessfully() {
    // Arrange
    Long scheduleId = 1L;
    var command = new DeleteScheduleCommand(scheduleId);

    when(scheduleRepository.existsById(scheduleId)).thenReturn(true);
    doNothing().when(scheduleRepository).deleteById(scheduleId);

    // Act
    scheduleCommandService.handle(command);

    // Assert
    verify(scheduleRepository, times(1)).existsById(scheduleId);
    verify(scheduleRepository, times(1)).deleteById(scheduleId);
    verifyNoMoreInteractions(scheduleRepository);
  }

  @Test
  @DisplayName("handle(DeleteScheduleCommand) should throw exception when schedule does not exist")
  void handle_DeleteSchedule_WhenScheduleDoesNotExist_ShouldThrowException() {
    // Arrange
    Long scheduleId = 999L;
    var command = new DeleteScheduleCommand(scheduleId);

    when(scheduleRepository.existsById(scheduleId)).thenReturn(false);

    // Act & Assert
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> scheduleCommandService.handle(command),
        "Should throw IllegalArgumentException when schedule not found"
    );

    assertEquals("Schedule not found", exception.getMessage(), "Exception message should match");
    verify(scheduleRepository, times(1)).existsById(scheduleId);
    verify(scheduleRepository, never()).deleteById(any());
    verifyNoMoreInteractions(scheduleRepository);
  }
}
