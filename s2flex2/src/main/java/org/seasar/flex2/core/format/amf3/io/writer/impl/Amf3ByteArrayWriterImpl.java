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

import org.seasar.flex2.core.format.amf3.Amf3Constants;
import org.seasar.flex2.core.format.amf3.type.Amf3TypeDef;
import org.seasar.flex2.core.format.amf3.type.ByteArray;

public class Amf3ByteArrayWriterImpl extends AbstractAmf3ObjectWriterImpl {

    public int getDataTypeValue() {
        return Amf3TypeDef.BYTEARRAY;
    }

    public boolean isWritableValue(final Object value) {
        return value instanceof ByteArray;
    }

    protected void writeInlineObject(final Object object,
            final DataOutputStream outputStream) throws IOException {
        writeByteArrayData((ByteArray) object, outputStream);
    }

    private final void writeByteArrayData(final ByteArray bytearray,
            final DataOutputStream outputStream) throws IOException {
        addObjectReference(bytearray);
        final byte[] buffer = bytearray.getBufferBytes();
        final int bytearrayDef = (buffer.length << 1)
                | Amf3Constants.OBJECT_INLINE;
        writeIntData(bytearrayDef, outputStream);

        if (buffer.length > 0) {
            outputStream.write(buffer, 0, buffer.length);
        }
    }
}