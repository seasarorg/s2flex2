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
import java.util.Date;

import org.seasar.flex2.rpc.amf.io.util.Amf3DataUtil;

public class Amf3DateReaderImpl extends AbstractAmf3ObjectReaderImpl {

    public Object read(final DataInputStream inputStream) throws IOException {
        return readObject(inputStream);
    }

    protected final Object readInlinedObject(final int reference,
            final DataInputStream inputStream) throws IOException {
        Date date = readDateData(inputStream);
        logger.debug("<amf3> read inline Date:" + date);
        return date;
    }

    protected final Object readReferencedObject(final int reference,
            final DataInputStream inputStream) throws IOException {
        logger.debug("<amf3> read reference Date:" + (reference >>> 1));
        return getObjectAt(reference >>> 1);
    }

    private final Date readDateData(final DataInputStream inputStream)
            throws IOException {
        Date date = Amf3DataUtil.toDate(inputStream.readDouble());
        addObjectReference(date);
        return date;
    }
}