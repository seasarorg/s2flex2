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
package org.seasar.flex2.rpc.remoting.processor.impl;

import javax.servlet.http.HttpServletRequest;

import org.seasar.flex2.rpc.remoting.message.RemotingMessageConstants;
import org.seasar.flex2.rpc.remoting.message.data.Message;
import org.seasar.flex2.rpc.remoting.message.data.MessageHeader;
import org.seasar.flex2.rpc.remoting.message.data.factory.MessageHeaderFactory;
import org.seasar.flex2.rpc.remoting.processor.RemotingMessageHeaderCreator;
import org.seasar.flex2.util.session.HttpSessionUtil;
import org.seasar.flex2.util.session.SessionDecorator;
import org.seasar.framework.container.S2Container;

public class AppendToGatewayUrlHeaderCreatorImpl implements
        RemotingMessageHeaderCreator {

    private S2Container container;

    private MessageHeaderFactory messageHeaderFactory;

    private SessionDecorator sessionDecorator;

    public MessageHeader createHeader(final Message requestMessage) {

        final HttpServletRequest request = (HttpServletRequest) container
                .getComponent(HttpServletRequest.class);

        MessageHeader header = null;

        if (!request.isRequestedSessionIdValid()) {
            final String sessionId = HttpSessionUtil.getSessionId(request,
                    false);
            header = messageHeaderFactory.createHeader(
                    RemotingMessageConstants.HEADER_APPEND_TO_GATEWAYURL,
                    sessionDecorator.formatSessionId(sessionId), false);
        }

        return header;
    }

    public MessageHeaderFactory getMessageHeaderFactory() {
        return messageHeaderFactory;
    }

    public SessionDecorator getSessionDecorator() {
        return sessionDecorator;
    }

    public void setMessageHeaderFactory(MessageHeaderFactory amfHeaderFactory) {
        this.messageHeaderFactory = amfHeaderFactory;
    }

    public void setSessionDecorator(SessionDecorator sessionDecorator) {
        this.sessionDecorator = sessionDecorator;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }
}