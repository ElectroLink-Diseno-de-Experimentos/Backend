package com.hampcoders.electrolink.assets.domain.model.queries;

/**
 * Query to retrieve inventories where stock quantity is below a specified threshold.
 */
public record GetInventoriesWithLowStockQuery(int threshold) {
}