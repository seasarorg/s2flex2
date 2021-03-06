/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.flex2.core.format.amf3.io.writer.impl;

import java.io.DataOutputStream;
import java.io.IOException;

import org.seasar.flex2.core.format.amf3.Amf3Constants;
import org.seasar.flex2.core.format.amf3.type.Amf3TypeDef;
import org.seasar.framework.aop.javassist.AspectWeaver;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;

public class Amf3TypedObjectWriterImpl extends
        AbstractAmf3ClassObjectWriterImpl {

    public int getDataTypeValue() {
        return Amf3TypeDef.OBJECT;
    }

    public boolean isWritableValue(final Object value) {
        return (value instanceof Object);
    }

    protected void writeClassDefine(final BeanDesc beanDesc,
            final DataOutputStream outputStream) throws IOException {
        int classDef = Amf3Constants.OBJECT_INLINE;
        classDef |= Amf3Constants.CLASS_DEF_INLINE;
        classDef |= Amf3Constants.OBJECT_PROPERTY_LIST_ENCODED;
        classDef = (beanDesc.getPropertyDescSize() << 4) | classDef;
        writeIntData(classDef, outputStream);
    }

    protected final void writeClassName(final Object object,
            final DataOutputStream outputStream) throws IOException {
        String type = object.getClass().getName();
        if (type.startsWith(AspectWeaver.PREFIX_ENHANCED_CLASS)) {
            int index = type.lastIndexOf(AspectWeaver.SUFFIX_ENHANCED_CLASS);
            if (index > 0) {
                type = type.substring(2, index);
            }
        }
        writeTypeString(type, outputStream);
    }

    protected final void writeClassObject(final Object value,
            final DataOutputStream outputStream) throws IOException {
        addObjectReference(value);
        writeClassReference(value, outputStream);
        writeClassObjectProperties(value, outputStream);
    }

    protected void writeClassObjectProperties(final Object value,
            final DataOutputStream outputStream) throws IOException {
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(value.getClass());
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            writeClassObjectProperty(beanDesc.getPropertyDesc(i), value,
                    outputStream);
        }
    }

    protected void writeClassProperties(final BeanDesc beanDesc,
            final DataOutputStream outputStream) throws IOException {
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            writeClassPropertyName(outputStream, beanDesc.getPropertyDesc(i));
        }
    }

    protected final void writeClassReferenceDefine(final Object object,
            final DataOutputStream outputStream) throws IOException {
        addClassReference(object.getClass());

        final BeanDesc beanDesc = BeanDescFactory
                .getBeanDesc(object.getClass());
        writeClassDefine(beanDesc, outputStream);
        writeClassName(object, outputStream);
        writeClassProperties(beanDesc, outputStream);
    }

    protected void writeInlineObject(final Object object,
            final DataOutputStream outputStream) throws IOException {
        writeClassObject(object, outputStream);
    }

    private final void writeClassObjectProperty(final PropertyDesc propertyDef,
            final Object value, final DataOutputStream outputStream)
            throws IOException {

        if("stackTrace".equals(propertyDef.getPropertyName())){
            return;
        }
        if (propertyDef.isReadable() ) {
            writeObjectElement(propertyDef.getValue(value), outputStream);
        }else if(propertyDef.hasReadMethod()){
            writeObjectElement(propertyDef.getValue(value), outputStream);
        }
    }

    private final void writeClassPropertyName(
            final DataOutputStream outputStream, final PropertyDesc propertyDef)
            throws IOException {
        if (propertyDef.isReadable()) {
            writeTypeString(propertyDef.getPropertyName(), outputStream);
        }
    }
}