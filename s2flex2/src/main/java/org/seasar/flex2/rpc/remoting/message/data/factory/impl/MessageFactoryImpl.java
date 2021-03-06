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
package org.seasar.flex2.rpc.remoting.message.data.factory.impl;

import org.seasar.flex2.rpc.remoting.message.data.Message;
import org.seasar.flex2.rpc.remoting.message.data.factory.MessageFactory;
import org.seasar.framework.container.S2Container;

public class MessageFactoryImpl implements MessageFactory {

    public static final String REQUEST_MESSAGE = "requestMessage";

    public static final String RESPONCE_MESSAGE = "responseMessage";

    private S2Container container;

    public Message createRequestMessage() {
        return createMessage(REQUEST_MESSAGE);
    }

    public Message createResponseMessage() {
        return createMessage(RESPONCE_MESSAGE);
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(final S2Container container) {
        this.container = container;
    }

    private final Message createMessage(final String messageComponentName) {
        final Message message = (Message) container
                .getComponent(messageComponentName);

        return message;
    }

}
