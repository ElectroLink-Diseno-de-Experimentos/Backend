package com.hampcoders.electrolink.monitoring.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public class ReportId implements Serializable {
    private final Long value;

    public ReportId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportId reportId = (ReportId) o;
        return Objects.equals(value, reportId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ReportId{" +
                "value=" + value +
                '}';
    }
}

