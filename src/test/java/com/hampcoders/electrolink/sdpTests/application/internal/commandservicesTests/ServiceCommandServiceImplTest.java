package com.hampcoders.electrolink.sdpTests.application.internal.commandservicesTests;

import com.hampcoders.electrolink.sdp.application.internal.commandservices.ServiceCommandServiceImpl;
import com.hampcoders.electrolink.sdp.domain.model.aggregates.ServiceEntity;
import com.hampcoders.electrolink.sdp.domain.model.commands.CreateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.DeleteServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.commands.UpdateServiceCommand;
import com.hampcoders.electrolink.sdp.domain.model.entities.ComponentQuantity;
import com.hampcoders.electrolink.sdp.domain.model.entities.Tag;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Policy;
import com.hampcoders.electrolink.sdp.domain.model.valueobjects.Restriction;
import com.hampcoders.electrolink.sdp.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceCommandServiceImplTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceCommandServiceImpl serviceCommandServiceImpl;

    private Restriction restriction;
    private Policy policy;
    private List<Tag> tags;
    private List<ComponentQuantity> components;
    private final Long MOCK_SERVICE_ID = 1L;

    @BeforeEach
    void setUp() {
        // Inicialización de datos comunes para evitar repetirlos en cada test
        restriction = new Restriction();
        policy = new Policy();

        // Convertimos las listas inmutables a ArrayLists mutables
        tags = new ArrayList<>(List.of(new Tag("Tag1"), new Tag("Tag2")));
        components = new ArrayList<>(List.of(new ComponentQuantity()));
    }

    // [--- Prueba para CreateServiceCommand ---]

    @Test
    @DisplayName("handle(CreateServiceCommand) debería crear y devolver el ID del servicio (AAA)")
    public void testHandleCreateServiceCommand_Success() {
        // Arrange
        var command = new CreateServiceCommand(
            "Service Name", "Service Description", 100.0, "60h", "Category",
            true, "Admin", policy, restriction, tags, components
        );

        // Creamos un mock de ServiceEntity para simular que ha sido guardado y tiene un ID.
        ServiceEntity savedServiceMock = mock(ServiceEntity.class);
        when(savedServiceMock.getId()).thenReturn(MOCK_SERVICE_ID);

        // Cuando se llame a save con *cualquier* ServiceEntity, devuelve el mock.
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(savedServiceMock);

        // Act
        Long serviceId = serviceCommandServiceImpl.handle(command);

        // Assert
        // 1. Verificar que el ID no es nulo y es el esperado.
        assertNotNull(serviceId, "El ID del servicio no debe ser nulo.");
        assertEquals(MOCK_SERVICE_ID, serviceId, "El ID del servicio devuelto debe coincidir.");

        // 2. Verificar que el metodo save del repositorio fue llamado exactamente una vez.
        verify(serviceRepository, times(1)).save(any(ServiceEntity.class));
    }

}
