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

import org.seasar.flex2.rpc.amf.data.Amf3Constants;
import org.seasar.flex2.rpc.amf.io.reader.data.AmfDataReader;
import org.seasar.flex2.rpc.amf.util.Amf3DataUtil;
import org.seasar.framework.util.ClassUtil;

import flashgateway.io.ASObject;

public class Amf3ObjectReaderImpl extends AbstractAmf3TypedObjectReaderImpl {

    private AmfDataReader stringReader;

    public Object read( final DataInputStream inputStream) throws IOException {
        return readObject( inputStream );
    }

    public void setStringReader(AmfDataReader stringReader) {
        this.stringReader = stringReader;
    }

    protected final Object readInlinedObject( final int reference, final DataInputStream inputStream) throws IOException {
        Object object = readObjectData(reference, inputStream ); 
        logger.debug("<amf3> read inline Object:");
        return object;
    }

    protected final Object readReferencedObject( final int reference, final DataInputStream inputStream) throws IOException {
        logger.debug("<amf3> read reference Object:" + (reference>>>1));
        return getObjectAt(reference >>> 1);
    }

    private final Object readASObjectData( final DataInputStream inputStream ) throws IOException {
        logger.debug("<amf3> read ASObject:");

        ASObject asobject = new ASObject();
        addObjectReference(asobject);
        while (true) {
            String propertyName = (String)stringReader.read( inputStream );
            if (propertyName.length() <= 0) {
                break;
            }
            //
            byte dataType = inputStream.readByte();
            Object value = writeElementData(dataType, inputStream);
            
            logger.debug("property=" + propertyName + ",value=" + value);
            asobject.put(propertyName, value);
        }
        return asobject;
    }

    private final Class readClassDef( final int objectDef, final DataInputStream inputStream ) throws IOException {

        Class clazz = ASObject.class;

        switch (objectDef & Amf3Constants.CLASS_DEF_INLINE) {

            case Amf3Constants.CLASS_DEF_REFERENCE:
                clazz = getClassAt(objectDef >>> 2);
                break;

            case Amf3Constants.CLASS_DEF_INLINE:
                String class_name = (String)stringReader.read( inputStream );
                if (class_name.length() > 0) {
                    clazz = ClassUtil.forName(class_name);
                }
                addClassReference(clazz);
                break;
        }
        
        return clazz;
    }

    private final Object readClassObjectData( final int objectDef, final Class clazz, final DataInputStream inputStream )
            throws IOException {
        logger.debug("<amf3> read tpyed class:" + clazz.getName());

        Object object = ClassUtil.newInstance(clazz);
        addObjectReference(object);

        String[] propertyNames = readPropertiesOfClass(objectDef, clazz, inputStream);

        Object[] propertyValues = new Object[propertyNames.length];
        for (int i = 0; i < propertyNames.length; i++) {
            byte dataType = inputStream.readByte();
            Object value = writeElementData(dataType, inputStream);
            
            logger.debug("property=" + propertyNames[ i ] + ",value=" + value);
            propertyValues[i] = value;
        }

        Amf3DataUtil.setProperties(object, propertyNames, propertyValues);

        return object;
    }

    private final Object readObjectData( final int objectDef, final DataInputStream inputStream ) throws IOException {
        Class clazz = readClassDef(objectDef,inputStream);
        
        Object object;
        if (clazz != null && !(ASObject.class.equals(clazz))) {
            object = readClassObjectData(objectDef, clazz, inputStream);
        } else {
            object = readASObjectData( inputStream );
        }
        
        return object;
    }

    private final String[] readPropertiesOfClass(final int objectDef, final Class clazz, final DataInputStream inputStream) throws IOException {
        String[] propertyNames;
        if ((objectDef & Amf3Constants.CLASS_DEF_INLINE) == Amf3Constants.CLASS_DEF_INLINE) {
            int propertyNumber = objectDef >>> 4;
            propertyNames = new String[propertyNumber];
            for (int i = 0; i < propertyNumber; i++) {
                propertyNames[i] = (String)stringReader.read( inputStream );
            }
            addClassProperties(clazz, propertyNames);
        } else {
            propertyNames = getPropertiesAt(clazz);
        }
        return propertyNames;
    }

}