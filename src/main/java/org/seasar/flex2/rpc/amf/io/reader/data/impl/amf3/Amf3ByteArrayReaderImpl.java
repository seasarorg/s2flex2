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
package org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3;

import java.io.DataInputStream;
import java.io.IOException;

import org.seasar.flex2.rpc.amf.data.ByteArray;
import org.seasar.flex2.rpc.amf.data.factory.ByteArrayFactory;

public class Amf3ByteArrayReaderImpl extends AbstractAmf3ObjectReaderImpl {
    
    private static final int READ_BUFFER_SIZE = 1024 * 8;

    private ByteArrayFactory byteArrayFactory;
    
    public ByteArrayFactory getByteArrayFactory() {
        return byteArrayFactory;
    }

    public Object read(final DataInputStream inputStream) throws IOException {
        return readObject(inputStream);
    }

    public void setByteArrayFactory(ByteArrayFactory byteArrayFactory) {
        this.byteArrayFactory = byteArrayFactory;
    }

    protected final Object readInlinedObject(final int reference,
            final DataInputStream inputStream) throws IOException {
        return readByteArrayData(reference, inputStream);
    }

    protected final Object readReferencedObject(final int reference,
            final DataInputStream inputStream) throws IOException {
        return getObjectAt(reference >>> 1);
    }

    private final ByteArray readByteArrayData(final int bytearrayDef,
            final DataInputStream inputStream) throws IOException {
        
        int bytearrayDataLength = bytearrayDef >> 1;
        int totalReadByteLength = 0;
        
        byte[] bytearrayData = new byte[bytearrayDataLength];
        byte[] readBuffer = new byte[READ_BUFFER_SIZE];
        
        while( totalReadByteLength < bytearrayDataLength ){
            int readByteLength = inputStream.read(readBuffer);
            System.arraycopy(readBuffer, 0, bytearrayData, totalReadByteLength, readByteLength);
            totalReadByteLength += readByteLength;
        }
        
        return byteArrayFactory.createByteArray(bytearrayData);
    }
}