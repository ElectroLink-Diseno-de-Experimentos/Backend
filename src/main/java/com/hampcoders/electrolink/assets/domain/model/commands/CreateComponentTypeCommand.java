package com.hampcoders.electrolink.assets.domain.model.commands;

/**
 * Command to create a new component type.
 */
public record CreateComponentTypeCommand(String name, String description) {
}