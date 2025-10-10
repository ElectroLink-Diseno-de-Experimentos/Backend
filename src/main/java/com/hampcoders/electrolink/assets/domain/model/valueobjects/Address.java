package com.hampcoders.electrolink.assets.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.lang.String;

/**
 * Represents a physical address with geographical coordinates.
 */
@Embeddable
public record Address(String street, String number, String city, String postalCode,
                      String country, float latitude, float longitude) {

  /**
   * Primary constructor for Address.
   *
   * @param street The street name.
   * @param number The street number.
   * @param city The city.
   * @param postalCode The postal code.
   * @param country The country.
   * @param latitude The latitude coordinate.
   * @param longitude The longitude coordinate.
   * @throws IllegalArgumentException if any required field is null or empty.
   */
  public Address {
    if (street == null || street.isBlank()) {
      throw new IllegalArgumentException("Street cannot be null or empty");
    }
    if (number == null || number.isBlank()) {
      throw new IllegalArgumentException("Number cannot be null or empty");
    }
    if (city == null || city.isBlank()) {
      throw new IllegalArgumentException("City cannot be null or empty");
    }
    if (postalCode == null || postalCode.isBlank()) {
      throw new IllegalArgumentException("Postal code cannot be null or empty");
    }
    if (country == null || country.isBlank()) {
      throw new IllegalArgumentException("Country cannot be null or empty");
    }
  }

  /**
   * Default constructor that creates an empty address.
   */
  public Address() {
    this("", "", "", "", "", 0.0f, 0.0f);
  }
}