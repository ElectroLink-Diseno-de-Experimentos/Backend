package com.hampcoders.electrolink.iam.interfaces.rest.resources;

/**
 * Represents the resource returned for a user role.
 *
 * @param id The role's ID.
 * @param name The role's name.
 */
public record RoleResource(Long id, String name) {
}