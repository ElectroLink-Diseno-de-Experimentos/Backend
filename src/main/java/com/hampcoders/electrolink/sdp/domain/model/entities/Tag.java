package com.hampcoders.electrolink.sdp.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a tag.
 */
@Entity
@Getter
@NoArgsConstructor
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  /**
   * Constructor for Tag.
   *
   * @param name The name of the tag.
   *
   */
  public Tag(String name) {
    this.name = name;
  }
}
