package com.hampcoders.electrolink.iam.application.internal.commandservices;

import com.hampcoders.electrolink.iam.application.internal.outboundservices.hashing.HashingService;
import com.hampcoders.electrolink.iam.application.internal.outboundservices.tokens.TokenService;
import com.hampcoders.electrolink.iam.domain.model.aggregates.User;
import com.hampcoders.electrolink.iam.domain.model.commands.SignInCommand;
import com.hampcoders.electrolink.iam.domain.model.commands.SignUpCommand;
import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.domain.model.valueobjects.Roles;
import com.hampcoders.electrolink.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.hampcoders.electrolink.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private HashingService hashingService;
    @Mock
    private TokenService tokenService;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserCommandServiceImpl userCommandService;

    @Test
    @DisplayName("handle(SignInCommand) should return user and token when credentials valid (AAA)")
    void handle_SignIn_ShouldReturnPair_WhenValid() {
        // Arrange
        var cmd = new SignInCommand("alice", "secret");
        var user = mock(User.class);
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn("hashed");
        when(hashingService.matches("secret", "hashed")).thenReturn(true);
        when(user.getUsername()).thenReturn("alice");
        when(tokenService.generateToken("alice")).thenReturn("jwt");

        // Act
        Optional<ImmutablePair<User, String>> result = userCommandService.handle(cmd);

        // Assert
        assertTrue(result.isPresent());
        assertSame(user, result.get().left);
        assertEquals("jwt", result.get().right);
        verify(userRepository).findByUsername("alice");
        verify(hashingService).matches("secret", "hashed");
        verify(tokenService).generateToken("alice");
        verifyNoMoreInteractions(userRepository, hashingService, tokenService);
        verifyNoInteractions(roleRepository);
    }

    @Test
    @DisplayName("handle(SignInCommand) should throw when user not found (AAA)")
    void handle_SignIn_ShouldThrow_WhenUserMissing() {
        // Arrange
        var cmd = new SignInCommand("bob", "x");
        when(userRepository.findByUsername("bob")).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(cmd));

        verify(userRepository).findByUsername("bob");
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(hashingService, tokenService, roleRepository);
    }

    @Test
    @DisplayName("handle(SignInCommand) should throw when password invalid (AAA)")
    void handle_SignIn_ShouldThrow_WhenPasswordInvalid() {
        // Arrange
        var cmd = new SignInCommand("alice", "wrong");
        var user = mock(User.class);
        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));
        when(user.getPassword()).thenReturn("hashed");
        when(hashingService.matches("wrong", "hashed")).thenReturn(false);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(cmd));

        verify(userRepository).findByUsername("alice");
        verify(hashingService).matches("wrong", "hashed");
        verifyNoMoreInteractions(userRepository, hashingService);
        verifyNoInteractions(tokenService, roleRepository);
    }

    @Test
    @DisplayName("handle(SignUpCommand) should create user when username not exists and roles valid (AAA)")
    void handle_SignUp_ShouldCreate_WhenValid() {
        // Arrange
        var requestedRoleHomeOwner = mock(Role.class);
        var requestedRoleClient = mock(Role.class);
        when(requestedRoleHomeOwner.getName()).thenReturn(Roles.ROLE_HOMEOWNER);
        when(requestedRoleClient.getName()).thenReturn(Roles.ROLE_CLIENT);
        var cmd = new SignUpCommand("charlie", "pwd", List.of(requestedRoleHomeOwner, requestedRoleClient));

        when(userRepository.existsByUsername("charlie")).thenReturn(false);
        var roleHomeOwner = mock(Role.class);
        var roleClient = mock(Role.class);
        when(roleRepository.findByName(Roles.ROLE_HOMEOWNER)).thenReturn(Optional.of(roleHomeOwner));
        when(roleRepository.findByName(Roles.ROLE_CLIENT)).thenReturn(Optional.of(roleClient));
        when(hashingService.encode("pwd")).thenReturn("hashedPwd");

        // userRepository.save returns the same entity
        doAnswer(inv -> null).when(userRepository).save(any(User.class));
        when(userRepository.findByUsername("charlie")).thenReturn(Optional.of(mock(User.class)));

        // Act
        Optional<User> result = userCommandService.handle(cmd);

        // Assert
        assertTrue(result.isPresent());
        verify(userRepository).existsByUsername("charlie");
        verify(roleRepository).findByName(Roles.ROLE_HOMEOWNER);
        verify(roleRepository).findByName(Roles.ROLE_CLIENT);
        verify(hashingService).encode("pwd");
        verify(userRepository).save(any(User.class));
        verify(userRepository).findByUsername("charlie");
        verifyNoMoreInteractions(userRepository, roleRepository, hashingService);
        verifyNoInteractions(tokenService);
    }

    @Test
    @DisplayName("handle(SignUpCommand) should throw when username exists (AAA)")
    void handle_SignUp_ShouldThrow_WhenUsernameExists() {
        // Arrange
        var cmd = new SignUpCommand("alice", "pwd", List.of());
        when(userRepository.existsByUsername("alice")).thenReturn(true);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(cmd));

        verify(userRepository).existsByUsername("alice");
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(roleRepository, hashingService, tokenService);
    }

    @Test
    @DisplayName("handle(SignUpCommand) should throw when some role name not found (AAA)")
    void handle_SignUp_ShouldThrow_WhenRoleNotFound() {
        // Arrange
        var requestedRoleTechnician = mock(Role.class);
        when(requestedRoleTechnician.getName()).thenReturn(Roles.ROLE_TECHNICIAN);
        var cmd = new SignUpCommand("new", "pwd", List.of(requestedRoleTechnician));
        when(userRepository.existsByUsername("new")).thenReturn(false);
        when(roleRepository.findByName(Roles.ROLE_TECHNICIAN)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> userCommandService.handle(cmd));

        verify(userRepository).existsByUsername("new");
        verify(roleRepository).findByName(Roles.ROLE_TECHNICIAN);
        verifyNoMoreInteractions(userRepository, roleRepository);
        verifyNoInteractions(hashingService, tokenService);
    }
}
