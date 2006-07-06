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
package org.seasar.flex2.rpc.remoting.message.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.flex2.rpc.remoting.message.data.Message;
import org.seasar.flex2.rpc.remoting.message.data.MessageBody;
import org.seasar.flex2.rpc.remoting.message.data.MessageHeader;

public class MessageImpl implements Message {

    private List bodies = new ArrayList();

    private List headers = new ArrayList();

    private int version = 0x03;

    public MessageImpl() {
    }

    public void addBody(MessageBody body) {
        bodies.add(body);
    }

    public void addHeader(MessageHeader body) {
        headers.add(body);
    }

    public MessageBody getBody(int index) {
        return (MessageBody) bodies.get(index);
    }

    public int getBodySize() {
        return bodies.size();
    }

    public MessageHeader getHeader(int index) {
        return (MessageHeader) headers.get(index);
    }

    public int getHeaderSize() {
        return headers.size();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}