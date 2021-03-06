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
package org.seasar.flex2.core.util;

import javax.xml.parsers.DocumentBuilder;

import org.seasar.flex2.core.format.amf3.type.CharsetType;
import org.seasar.framework.util.DocumentBuilderFactoryUtil;
import org.seasar.framework.util.DocumentBuilderUtil;
import org.seasar.framework.util.DomUtil;
import org.w3c.dom.Document;

public class XmlStringUtil {
    public static final Document getXmlDocument(final String xml) {
        final DocumentBuilder builder = DocumentBuilderFactoryUtil
                .newDocumentBuilder();
        return DocumentBuilderUtil.parse(builder, DomUtil.getContentsAsStream(
                xml, CharsetType.UTF8));
    }

    public static final String getXmlString(final Document document) {
        return DomUtil.toString(document.getDocumentElement());
    }
}
