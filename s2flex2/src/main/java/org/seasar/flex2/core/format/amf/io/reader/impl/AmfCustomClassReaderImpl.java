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
package org.seasar.flex2.core.format.amf.io.reader.impl;

import java.io.DataInputStream;
import java.io.IOException;

import org.seasar.flex2.core.format.amf.type.AmfTypeDef;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.ClassUtil;

public class AmfCustomClassReaderImpl extends AbstractAmfClassObjectReaderImpl {

    private static final Object createCustomObject(
            final DataInputStream inputStream) throws IOException {
        final Class clazz = ClassUtil.forName(inputStream.readUTF());
        return ClassUtil.newInstance(clazz);
    }

    public Object read(final DataInputStream inputStream) throws IOException {
        return readCustomClass(inputStream);
    }

    private final Object readCustomClass(final DataInputStream inputStream)
            throws IOException {
        final Object bean = createCustomObject(inputStream);
        addSharedObject(bean);
        readProperties(inputStream, bean);
        return bean;
    }

    private final void readProperties(final DataInputStream inputStream,
            final Object bean) throws IOException {
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(bean.getClass());
        while (true) {
            String key = inputStream.readUTF();
            byte dataType = inputStream.readByte();
            if (dataType == AmfTypeDef.EOM) {
                break;
            }
            if (beanDesc.hasPropertyDesc(key)) {
                PropertyDesc pd = beanDesc.getPropertyDesc(key);
                if (pd.hasWriteMethod()) {
                    pd.setValue(bean, readData(dataType, inputStream));
                }
            }
        }
    }
}