package com.gora.contractmanagerapi.infra;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

public class UUIDWrapperDescriptor<K extends UUIDWrapper> extends AbstractTypeDescriptor<K> {
    private static final long serialVersionUID = 8625043768156425743L;
    private final transient Function<UUID, K> factory;
    private transient Comparator<K> comaparator;

    public UUIDWrapperDescriptor(Class<K> type, Function<UUID, K> factory) {
        super(type);
        this.factory = (Function)Objects.requireNonNull(factory, "factory must not be null.");
    }

    public String toString(K value) {
        return value.asString();
    }

    public K fromString(String string) {
        return (K)(this.factory.apply(UUIDTypeDescriptor.INSTANCE.fromString(string)));
    }

    public <X> X unwrap(K value, Class<X> type, WrapperOptions options) {
        return (X)(Objects.isNull(value) ? null : UUIDTypeDescriptor.INSTANCE.unwrap(value.asUUID(), type, options));
    }

    public <X> K wrap(X value, WrapperOptions options) {
        return (K)(Objects.isNull(value) ? null : (UUIDWrapper)this.factory.apply(UUIDTypeDescriptor.INSTANCE.wrap(value, options)));
    }

    public Comparator<K> getComparator() {
        if (Objects.isNull(this.comaparator)) {
            this.comaparator = Comparator.comparing(UUIDWrapper::asUUID, (v1, v2) -> v2.compareTo(v1));
        }

        return this.comaparator;
    }
}