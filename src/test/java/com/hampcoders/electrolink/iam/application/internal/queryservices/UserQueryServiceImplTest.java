package com.hampcoders.electrolink.iam.application.internal.queryservices;

import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.queries.GetAllUsersQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByIdQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetUserByUsernameQuery;
import com.hampcoders.electrolink.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserQueryServiceImpl userQueryService;

    @Test
    @DisplayName("handle(GetAllUsersQuery) returns list from repository (AAA)")
    void handle_GetAll_ShouldReturnList() {
        // Arrange
        var query = new GetAllUsersQuery();
        User a = mock(User.class);
        User b = mock(User.class);
        when(userRepository.findAll()).thenReturn(List.of(a, b));

        // Act
        var actual = userQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size());
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("handle(GetAllUsersQuery) returns empty list when repository is empty (AAA)")
    void handle_GetAll_ShouldReturnEmptyList() {
        // Arrange
        var query = new GetAllUsersQuery();

        when(userRepository.findAll()).thenReturn(List.of());

        // Act
        var actual = userQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "The returned list should be empty.");
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("handle(GetUserByIdQuery) returns Optional from repository (AAA)")
    void handle_GetById_ShouldReturnOptional() {
        // Arrange
        var query = new GetUserByIdQuery(10L);
        User expected = mock(User.class);
        when(userRepository.findById(10L)).thenReturn(Optional.of(expected));

        // Act
        Optional<User> actual = userQueryService.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expected, actual.get());
        verify(userRepository).findById(10L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("handle(GetUserByIdQuery) returns empty Optional when user not found (AAA)")
    void handle_GetById_ShouldReturnEmptyOptional() {
        // Arrange
        var query = new GetUserByIdQuery(99L);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<User> actual = userQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "The Optional should be empty when the user is not found.");
        verify(userRepository).findById(99L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("handle(GetUserByUsernameQuery) returns Optional from repository (AAA)")
    void handle_GetByUsername_ShouldReturnOptional() {
        // Arrange
        var query = new GetUserByUsernameQuery("alice");
        User expected = mock(User.class);
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(expected));

        // Act
        Optional<User> actual = userQueryService.handle(query);

        // Assert
        assertTrue(actual.isPresent());
        assertSame(expected, actual.get());
        verify(userRepository).findByUsername("alice");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("handle(GetUserByUsernameQuery) returns empty Optional when user not found (AAA)")
    void handle_GetByUsername_ShouldReturnEmptyOptional() {
        // Arrange
        var query = new GetUserByUsernameQuery("nonexistent_user");
        when(userRepository.findByUsername("nonexistent_user")).thenReturn(Optional.empty());

        // Act
        Optional<User> actual = userQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "The Optional should be empty when the user is not found.");
        verify(userRepository).findByUsername("nonexistent_user");
        verifyNoMoreInteractions(userRepository);
    }
}
