package com.hampcoders.electrolink.iam.interfaces.rest.transform;

import com.hampcoders.electrolink.iam.domain.model.commands.SignUpCommand;
import com.hampcoders.electrolink.iam.domain.model.entities.Role;
import com.hampcoders.electrolink.iam.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        // 1. Convertir los nombres de String a entidades temporales Role
        var roles = resource.roles() != null
                ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList()
                : null; // Dejar como 'null' para que validateRoleSet lo maneje.

        // 2. Validar y obtener el rol por defecto si la lista es nula/vacía
        var validatedRoles = Role.validateRoleSet(roles);

        // 3. Crear el comando con la lista de roles validada (o por defecto)
        return new SignUpCommand(resource.username(), resource.password(), validatedRoles);
    }
}