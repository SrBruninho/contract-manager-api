package com.gora.contractmanagerapi.infra;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UUIDWrapper implements Serializable {

    @JsonIgnore
    private transient int hash;

    @JsonValue
    private final UUID value;

    @JsonCreator
    protected UUIDWrapper(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public String asString() {
        return (Objects.requireNonNullElse(this.value, "")).toString();
    }

    public UUID asUUID() {
        return this.value;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj != null && this.getClass().equals(obj.getClass()) ? Objects.equals(this.value, ((UUIDWrapper)obj).value) : false;
        }
    }

    public final int hashCode() {
        if (this.hash == 0) {
            this.hash = Objects.hash(this.value);
        }

        return this.hash;
    }

    public final String toString() {
        String var = this.getClass().getSimpleName();
        return var + "[" + this.asString() + "]";
    }
}
