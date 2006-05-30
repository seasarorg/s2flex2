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
package org.seasar.flex2.rpc.amf.io.writer.factory.impl;

import java.io.DataOutputStream;

import org.seasar.flex2.rpc.amf.data.AmfMessage;
import org.seasar.flex2.rpc.amf.io.factory.AmfSharedObjectFactory;
import org.seasar.flex2.rpc.amf.io.writer.AmfWriter;
import org.seasar.flex2.rpc.amf.io.writer.factory.AmfWriterFactory;
import org.seasar.flex2.rpc.amf.io.writer.impl.AmfWriterImpl;
import org.seasar.framework.container.S2Container;

public class AmfWriterFactoryImpl implements AmfWriterFactory {

    private S2Container container;

    private AmfSharedObjectFactory sharedObjectFactory;

    public AmfWriter createWriter(final DataOutputStream dataOutputStream,
            final AmfMessage message) {
        AmfWriterImpl writer = (AmfWriterImpl) container
                .getComponent(AmfWriterImpl.class);
        writer.config(message, dataOutputStream);
        writer.setSharedObject(sharedObjectFactory.createSharedObject());
        return writer;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public void setSharedObjectFactory(
            AmfSharedObjectFactory sharedObjectFactory) {
        this.sharedObjectFactory = sharedObjectFactory;
    }
}