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

    // [--- Pruebas para UpdateServiceCommand ---]

    @Test
    @DisplayName("handle(UpdateServiceCommand) debería actualizar el servicio existente (AAA)")
    public void testHandleUpdateServiceCommand_Success() {
        // Arrange
        // 1. Crear el comando con los nuevos datos
        var command = new UpdateServiceCommand(
            MOCK_SERVICE_ID, "Updated Name", "Updated Description", 150.0,
            "90h", "Updated Category", false, "Admin", policy, restriction, tags, components
        );

        // 2. Crear un mock de la entidad existente que se 'encontrará' en el repositorio
        // Usamos spy() para poder llamar a métodos reales como updateFrom()
        ServiceEntity existingService = Mockito.spy(new ServiceEntity(
            "Old Name", "Old Description", 100.0, "60h", "Category",
            true, "Admin", policy, restriction, tags, components
        ));

        // Simular que el repositorio encuentra la entidad
        when(serviceRepository.findById(MOCK_SERVICE_ID)).thenReturn(Optional.of(existingService));

        // Simular el comportamiento de save (podemos devolver la misma entidad)
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(existingService);

        // Act
        serviceCommandServiceImpl.handle(command);

        // Assert
        // 1. Verificar que se buscó por ID.
        verify(serviceRepository, times(1)).findById(MOCK_SERVICE_ID);

        // 2. Verificar que se llamó al metodo updateFrom() de la entidad.
        verify(existingService, times(1)).updateFrom(any(ServiceEntity.class));

        // 3. Verificar que se llamó a save para persistir los cambios.
        verify(serviceRepository, times(1)).save(existingService);

        // 4. (Opcional) Verificar que los datos de la entidad se actualizaron.
        assertEquals(command.name(), existingService.getName());
        assertEquals(command.description(), existingService.getDescription());
    }

    @Test
    @DisplayName("handle(UpdateServiceCommand) debería lanzar excepción si el servicio no existe (AAA)")
    public void testHandleUpdateServiceCommand_ServiceNotFound() {
        // Arrange
        var command = new UpdateServiceCommand(
            MOCK_SERVICE_ID, "Name", "Desc", 100.0,
            "60h", "Category", true, "Admin", policy, restriction, tags, components
        );

        // Simular que el repositorio NO encuentra la entidad
        when(serviceRepository.findById(MOCK_SERVICE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        // 1. Verificar que se lanza la excepción correcta.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceCommandServiceImpl.handle(command);
        });

        // 2. Verificar el mensaje de la excepción.
        String expectedMessage = "Service not found with id: " + MOCK_SERVICE_ID;
        assertEquals(expectedMessage, exception.getMessage());
        // 3. Verificar que save nunca se llamó.
        verify(serviceRepository, never()).save(any(ServiceEntity.class));
    }

    // [--- Pruebas para DeleteServiceCommand ---]

    @Test
    @DisplayName("handle(DeleteServiceCommand) debería eliminar el servicio existente (AAA)")
    public void testHandleDeleteServiceCommand_Success() {
        // Arrange
        var command = new DeleteServiceCommand(MOCK_SERVICE_ID);

        // Simular que el repositorio confirma que la entidad existe
        when(serviceRepository.existsById(MOCK_SERVICE_ID)).thenReturn(true);

        // Configuramos la eliminación para que no haga nada (void method)
        doNothing().when(serviceRepository).deleteById(MOCK_SERVICE_ID);

        // Act
        serviceCommandServiceImpl.handle(command);

        // Assert
        // 1. Verificar que se comprobó la existencia del servicio.
        verify(serviceRepository, times(1)).existsById(MOCK_SERVICE_ID);

        // 2. Verificar que se llamó al metodo deleteById exactamente una vez.
        verify(serviceRepository, times(1)).deleteById(MOCK_SERVICE_ID);
    }

    @Test
    @DisplayName("handle(DeleteServiceCommand) debería lanzar excepción si el servicio no existe (AAA)")
    public void testHandleDeleteServiceCommand_ServiceNotFound() {
        // Arrange
        var command = new DeleteServiceCommand(MOCK_SERVICE_ID);

        // Simular que el repositorio confirma que la entidad NO existe
        when(serviceRepository.existsById(MOCK_SERVICE_ID)).thenReturn(false);

        // Act & Assert
        // 1. Verificar que se lanza la excepción correcta.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            serviceCommandServiceImpl.handle(command);
        });

        // 2. Verificar el mensaje de la excepción.
        String expectedMessage = "Service not found with id: " + MOCK_SERVICE_ID;
        assertEquals(expectedMessage, exception.getMessage());
        // 3. Verificar que deleteById nunca se llamó.
        verify(serviceRepository, never()).deleteById(MOCK_SERVICE_ID);
    }
}
