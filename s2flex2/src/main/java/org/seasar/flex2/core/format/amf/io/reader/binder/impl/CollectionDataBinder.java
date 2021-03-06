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
package org.seasar.flex2.core.format.amf.io.reader.binder.impl;

import java.util.Collection;

import org.seasar.flex2.core.format.amf.io.reader.binder.DataBinder;
import org.seasar.flex2.util.converter.Converter;

public class CollectionDataBinder implements DataBinder {
    
    private Converter collectionConverter;

    public final Object bind(final Object source, final Class bindClass) {
        return collectionConverter.convert(source, bindClass);
    }

    public boolean isTarget(final Object targetValue, final Class bindClass) {
        return (targetValue != null) && (targetValue.getClass().isArray())
                && (Collection.class.isAssignableFrom(bindClass));
    }

    public void setCollectionConverter(final Converter collectionConverter) {
        this.collectionConverter = collectionConverter;
    }
}
