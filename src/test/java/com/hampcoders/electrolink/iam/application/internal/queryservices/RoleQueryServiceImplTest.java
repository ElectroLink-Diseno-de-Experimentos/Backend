package com.hampcoders.electrolink.iam.application.internal.queryservices;

import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.domain.model.queries.GetAllRolesQuery;
import com.hampcoders.electrolink.iam.domain.model.queries.GetRoleByNameQuery;
import com.hampcoders.electrolink.iam.domain.model.valueobjects.Roles;
import com.hampcoders.electrolink.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
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
public class RoleQueryServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleQueryServiceImpl roleQueryService;

    @Test
    @DisplayName("handle(GetAllRolesQuery) should return list from repository (AAA)")
    void handle_GetAll_ShouldReturnList() {
        // Arrange
        var query = new GetAllRolesQuery();
        Role a = mock(Role.class);
        Role b = mock(Role.class);
        when(roleRepository.findAll()).thenReturn(List.of(a, b));

        // Act
        var actual = roleQueryService.handle(query);

        // Assert
        assertEquals(2, actual.size());
        assertSame(a, actual.get(0));
        assertSame(b, actual.get(1));
        verify(roleRepository).findAll();
        verifyNoMoreInteractions(roleRepository);
    }

    @Test
    @DisplayName("handle(GetAllRolesQuery) should return empty list when repository is empty (AAA)")
    void handle_GetAll_ShouldReturnEmptyList_WhenRepositoryIsEmpty() {
        // Arrange
        var query = new GetAllRolesQuery();

        when(roleRepository.findAll()).thenReturn(List.of());

        // Act
        var actual = roleQueryService.handle(query);

        // Assert
        assertTrue(actual.isEmpty(), "The returned list should be empty.");
        verify(roleRepository).findAll();
        verifyNoMoreInteractions(roleRepository);
    }

    @Test
    @DisplayName("handle(GetRoleByNameQuery) should return Optional from repository (AAA)")
    void handle_GetByName_ShouldReturnOptional() {
        // Arrange
        var query = new GetRoleByNameQuery(Roles.ROLE_CLIENT);
        Role expected = mock(Role.class);
        when(roleRepository.findByName(Roles.ROLE_CLIENT)).thenReturn(Optional.of(expected));

        // Act
        Optional<Role> result = roleQueryService.handle(query);

        // Assert
        assertTrue(result.isPresent());
        assertSame(expected, result.get());
        verify(roleRepository).findByName(Roles.ROLE_CLIENT);
        verifyNoMoreInteractions(roleRepository);
    }

    @Test
    @DisplayName("handle(GetRoleByNameQuery) should return empty Optional when role not found (AAA)")
    void handle_GetByName_ShouldReturnEmptyOptional_WhenNotFound() {
        // Arrange
        var query = new GetRoleByNameQuery(Roles.ROLE_HOMEOWNER);

        when(roleRepository.findByName(Roles.ROLE_HOMEOWNER)).thenReturn(Optional.empty());

        // Act
        Optional<Role> result = roleQueryService.handle(query);

        // Assert
        assertTrue(result.isEmpty(), "The Optional should be empty when the role is not found.");
        verify(roleRepository).findByName(Roles.ROLE_HOMEOWNER);
        verifyNoMoreInteractions(roleRepository);
    }
}
