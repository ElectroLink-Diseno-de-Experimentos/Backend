package com.hampcoders.electrolink.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Value object representing the unique identifier of a service request.
 * <p>
 * This class is marked as {@link Embeddable} for use in JPA entities.
 * </p>
 */
@Embeddable
public class RequestId implements Serializable {

  private Long requestId;

  /**
   * Default constructor required by JPA.
   */
  public RequestId() {
  }

  /**
   * Creates a RequestId with the given ID.
   *
   * @param id the request identifier
   */
  public RequestId(Long id) {
    this.requestId = id;
  }

  /**
   * Returns the underlying request ID.
   *
   * @return the request ID as Long
   */
  public Long getId() {
    return requestId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RequestId)) {
      return false;
    }
    RequestId that = (RequestId) o;
    return Objects.equals(requestId, that.requestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId);
  }
}