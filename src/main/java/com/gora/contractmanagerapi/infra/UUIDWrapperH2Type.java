package com.gora.contractmanagerapi.infra;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.io.Serial;
import java.io.Serializable;

public abstract class UUIDWrapperH2Type<K extends UUIDWrapper>
        extends AbstractSingleColumnStandardBasicType<K> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UUIDWrapperH2Type(UUIDWrapperDescriptor<K> descriptor) {
        super((JdbcType) VarcharJdbcType.INSTANCE, (JavaType<K>) descriptor);
    }

    public String getName() {
        var type = this.getJavaTypeDescriptor().getJavaType();
        return (type instanceof Class<?> c) ? c.getSimpleName() : type.getTypeName();
    }
}
