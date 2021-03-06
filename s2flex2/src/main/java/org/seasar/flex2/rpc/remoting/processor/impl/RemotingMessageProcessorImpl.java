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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.flex2.rpc.remoting.message.processor.MessageProcessor;
import org.seasar.flex2.rpc.remoting.processor.RemotingMessageProcessor;
import org.seasar.flex2.util.io.InputStreamUtil;
import org.seasar.flex2.util.io.OutputStreamUtil;

public class RemotingMessageProcessorImpl implements RemotingMessageProcessor {

    private MessageProcessor messageProcessor;

    public MessageProcessor getMessageProcessor() {
        return messageProcessor;
    }

    public void process(final HttpServletRequest request,
            final HttpServletResponse response) throws IOException,
            ServletException {

        try {
            final DataInputStream inputStream = InputStreamUtil
                    .toBufferedDataInputStream(request.getInputStream());
            final DataOutputStream outputStream = OutputStreamUtil
                    .toBufferedDataOutputStream(response.getOutputStream());

            messageProcessor.process(inputStream, outputStream);

            response.setContentLength(outputStream.size());
            outputStream.flush();

        } catch (final Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            } else if (throwable instanceof Error) {
                throw (Error) throwable;
            } else if (throwable instanceof IOException) {
                throw (IOException) throwable;
            } else if (throwable instanceof ServletException) {
                throw (ServletException) throwable;
            } else {
                throw new ServletException(throwable);
            }
        }
    }

    public void setMessageProcessor(final MessageProcessor processor) {
        this.messageProcessor = processor;
    }
}