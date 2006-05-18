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
package org.seasar.flex2.rpc.amf.io.reader.data.factory;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.flex2.rpc.amf.data.Amf3DataType;
import org.seasar.flex2.rpc.amf.data.AmfDataType;
import org.seasar.flex2.rpc.amf.io.reader.data.AmfDataReader;
import org.seasar.flex2.rpc.amf.io.reader.data.factory.impl.Amf3DataReaderFactoryImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf.AmfArrayReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf.AmfBooleanReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf.AmfNullReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf.AmfNumberReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf.AmfStringReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3ArrayReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3BooleanFalseReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3BooleanTrueReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3DataReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3DateReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3IntegerReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3ObjectReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3StringReaderImpl;
import org.seasar.flex2.rpc.amf.io.reader.data.impl.amf3.Amf3XmlReaderImpl;
import org.seasar.framework.container.S2Container;

public class Amf3DataReaderFactoryTest extends S2TestCase {

    private static String PATH = "amf3_reader_framework.dicon";

    public void testCreateDataReaderFactory() throws Exception {
        S2Container container = getContainer();
        Object factory = container.getComponent(AmfDataReaderFactory.class);
        assertNotNull("1", factory);
        assertTrue("2", factory instanceof Amf3DataReaderFactoryImpl);
    }

    public void testCreateDataReader() throws Exception {
        S2Container container = getContainer();
        AmfDataReaderFactory factory = (AmfDataReaderFactory) container
                .getComponent(Amf3DataReaderFactory.class);

        AmfDataReader reader;

        reader = factory.createDataReader(AmfDataType.NUMBER);
        assertTrue("1", reader instanceof AmfNumberReaderImpl);

        reader = factory.createDataReader(AmfDataType.BOOLEAN);
        assertTrue("2", reader instanceof AmfBooleanReaderImpl);

        reader = factory.createDataReader(AmfDataType.STRING);
        assertTrue("3", reader instanceof AmfStringReaderImpl);

        reader = factory.createDataReader(AmfDataType.NULL);
        assertTrue("5", reader instanceof AmfNullReaderImpl);

        reader = factory.createDataReader(AmfDataType.ARRAY);
        assertTrue("6", reader instanceof AmfArrayReaderImpl);

        reader = factory.createDataReader(Amf3DataType.AMF3_DATA_MARKER);
        assertTrue("8", reader instanceof Amf3DataReaderImpl);

    }

    public void testCreateAmf3DataReader() throws Exception {
        S2Container container = getContainer();
        Amf3DataReaderFactory factory = (Amf3DataReaderFactory) container
                .getComponent(AmfDataReaderFactory.class);

        AmfDataReader reader;

        reader = factory.createAmf3DataReader(Amf3DataType.NUMBER);
        assertTrue("1", reader instanceof AmfNumberReaderImpl);
        
        reader = factory.createAmf3DataReader(Amf3DataType.INTEGER);
        assertTrue("2", reader instanceof Amf3IntegerReaderImpl);
        
        reader = factory.createAmf3DataReader(Amf3DataType.BOOLEAN_FALSE);
        assertTrue("3", reader instanceof Amf3BooleanFalseReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.BOOLEAN_TRUE);
        assertTrue("4", reader instanceof Amf3BooleanTrueReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.STRING);
        assertTrue("5", reader instanceof Amf3StringReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.OBJECT);
        assertTrue("6", reader instanceof Amf3ObjectReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.NULL);
        assertTrue("7", reader instanceof AmfNullReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.ARRAY);
        assertTrue("8", reader instanceof Amf3ArrayReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.DATE);
        assertTrue("9", reader instanceof Amf3DateReaderImpl);

        reader = factory.createAmf3DataReader(Amf3DataType.XML);
        assertTrue("10", reader instanceof Amf3XmlReaderImpl);
    }

    protected void setUp() throws Exception {
        include(PATH);
    }

    protected void tearDown() throws Exception {
    }
}