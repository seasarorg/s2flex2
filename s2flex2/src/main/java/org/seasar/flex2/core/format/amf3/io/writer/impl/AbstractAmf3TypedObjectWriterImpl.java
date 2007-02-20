/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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

import org.seasar.flex2.core.format.amf3.io.writer.Amf3DataWriter;
import org.seasar.flex2.core.format.amf3.io.writer.factory.Amf3DataWriterFactory;

public abstract class AbstractAmf3TypedObjectWriterImpl extends
        AbstractAmf3ObjectWriterImpl {

    protected Amf3DataWriterFactory writerFactory;

    public void setWriterFactory(final Amf3DataWriterFactory writerFactory) {
        this.writerFactory = writerFactory;
    }

    protected void writeObjectElement(final Object value,
            final DataOutputStream outputStream) throws IOException {
        final Amf3DataWriter dataWriter = writerFactory
                .createAmf3DataWriter(value);
        dataWriter.writeData(value, outputStream);
    }
}