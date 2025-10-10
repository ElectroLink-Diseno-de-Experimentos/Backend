package com.hampcoders.electrolink.assets.domain.model.queries;

/**
 * Query to search for components whose name matches a given term, limited by a count.
 */
public record GetComponentsByNameQuery(String term, int limit) {}