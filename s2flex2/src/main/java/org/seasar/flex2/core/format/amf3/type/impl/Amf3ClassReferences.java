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
package org.seasar.flex2.core.format.amf3.type.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.util.ArrayMap;

public class Amf3ClassReferences {

    private static final int REFERENCE_SIZE = 32;

    private final List classReferences;

    private final Map propertiesReferences;

    public Amf3ClassReferences() {
        classReferences = new ArrayList(REFERENCE_SIZE);
        propertiesReferences = new ArrayMap(REFERENCE_SIZE);
    }

    public void addClassReference(final Class clazz) {
        classReferences.add(clazz);
    }

    public void addProperties(final Class clazz, final String[] properties) {
        propertiesReferences.put(clazz, properties);
    }

    public Class getClassAt(final int index) {
        final Object reference = classReferences.get(index);
        if (reference != null) {
            return (Class) reference;
        } else {
            return null;
        }
    }

    public String[] getPropertiesAt(final Class clazz) {
        String[] reference = null;
        if (propertiesReferences.containsKey(clazz)) {
            reference = (String[]) propertiesReferences.get(clazz);
        }
        return reference;
    }

    public int getReferenceIndex(final Class clazz) {
        return classReferences.indexOf(clazz);
    }

    public void initialize() {
        classReferences.clear();
        propertiesReferences.clear();
    }
}
