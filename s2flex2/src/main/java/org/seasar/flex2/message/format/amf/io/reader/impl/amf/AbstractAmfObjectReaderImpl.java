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
package org.seasar.flex2.message.format.amf.io.reader.impl.amf;

import java.io.DataInputStream;
import java.io.IOException;

import org.seasar.flex2.message.format.amf.io.AmfSharedObject;
import org.seasar.flex2.message.format.amf.io.factory.AmfSharedObjectFactory;
import org.seasar.flex2.message.format.amf.io.reader.AmfDataReader;
import org.seasar.flex2.message.format.amf.io.reader.factory.AmfDataReaderFactory;

public abstract class AbstractAmfObjectReaderImpl implements AmfDataReader {

    private AmfDataReaderFactory dataReaderFactory;

    private AmfSharedObjectFactory sharedObjectFactory;

    public AmfDataReaderFactory getDataReaderFactory() {
        return dataReaderFactory;
    }

    public AmfSharedObjectFactory getSharedObjectFactory() {
        return sharedObjectFactory;
    }

    public void setDataReaderFactory(AmfDataReaderFactory dataReaderFactory) {
        this.dataReaderFactory = dataReaderFactory;
    }

    public void setSharedObjectFactory(
            AmfSharedObjectFactory sharedObjectFactory) {
        this.sharedObjectFactory = sharedObjectFactory;
    }

    protected final void addSharedObject(Object value) {
        getSharedObject().addSharedObject(value);
    }

    protected final AmfSharedObject getSharedObject() {
        return sharedObjectFactory.createSharedObject();
    }

    protected final Object readData(final byte dataType,
            final DataInputStream inputStream) throws IOException {
        AmfDataReader dataReader = dataReaderFactory.createDataReader(dataType);
        return dataReader.read(inputStream);
    }
}