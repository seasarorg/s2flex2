/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.flex2.message.format.amf.io.reader.impl.amf3;

import java.io.DataInputStream;
import java.io.IOException;

import org.seasar.flex2.message.format.amf.Amf3Constants;
import org.seasar.flex2.message.format.amf.io.Amf3References;
import org.seasar.flex2.message.format.amf.io.factory.Amf3ReferencesFactory;

public abstract class AbstractAmf3ObjectReaderImpl extends
        AbstractAmf3IntReaderImpl {

    protected Amf3ReferencesFactory referencesFactory;

    public void setReferencesFactory(Amf3ReferencesFactory referencesFactory) {
        this.referencesFactory = referencesFactory;
    }

    protected final void addClassProperties(Class clazz, String[] properties) {
        getReferences().addClassProperties(clazz,
                properties);
    }

    protected final void addClassReference(Class clazz) {
        getReferences().addClassReference(clazz);
    }

    protected final void addObjectReference(Object object) {
        getReferences().addObjectReference(object);
    }

    protected final void addStringReference(String object) {
        getReferences().addStringReference(object);
    }

    protected final Class getClassAt(int index) {
        return getReferences().getClassAt(index);
    }

    protected final int getClassReferenceIndex(Class clazz) {
        return getReferences().getClassReferenceIndex(
                clazz);
    }

    protected final Object getObjectAt(int index) {
        return getReferences().getObjectAt(index);
    }

    protected final int getObjectReferenceIndex(Object object) {
        return getReferences().getObjectReferenceIndex(
                object);
    }

    protected final String[] getPropertiesOf(Class clazz) {
        return getReferences().getPropertiesAt(clazz);
    }

    protected final String getStringAt(int index) {
        return getReferences().getStringAt(index);
    }

    protected final int getStringReferenceIndex(String object) {
        return getReferences().getStringReferenceIndex(
                object);
    }

    abstract protected Object readInlinedObject(int reference,
            DataInputStream inputStream) throws IOException;

    protected final Object readObject(final DataInputStream inputStream)
            throws IOException {
        Object result = null;

        int reference = readInt(inputStream);
        switch (reference & Amf3Constants.OBJECT_INLINE) {

            case Amf3Constants.OBJECT_REFERENCE:
                result = readReferencedObject(reference, inputStream);
                break;

            case Amf3Constants.OBJECT_INLINE:
                result = readInlinedObject(reference, inputStream);
                break;

            default:
        }
        return result;
    }

    abstract protected Object readReferencedObject(int reference,
            DataInputStream inputStream) throws IOException;

    private final Amf3References getReferences() {
        return referencesFactory.createReferences();
    }
}