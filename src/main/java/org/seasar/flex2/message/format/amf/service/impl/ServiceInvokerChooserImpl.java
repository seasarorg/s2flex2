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
package org.seasar.flex2.message.format.amf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.flex2.message.format.amf.data.AmfBody;
import org.seasar.flex2.message.format.amf.service.ServiceInvoker;
import org.seasar.flex2.message.format.amf.service.ServiceInvokerChooser;
import org.seasar.flex2.message.format.amf.service.exception.InvokerNotFoundRuntimeException;

public class ServiceInvokerChooserImpl implements ServiceInvokerChooser {

    private final List invokers;

    public ServiceInvokerChooserImpl() {
        invokers = new ArrayList();
    }

    public void addInvoker(ServiceInvoker invoker) {
        invokers.add(invoker);
    }

    public ServiceInvoker chooseInvoker( final AmfBody requestBody) {
        ServiceInvoker invoker;
        for (int i = 0; i < invokers.size(); ++i) {
            invoker = (ServiceInvoker) invokers.get(i);
            if (invoker.supports(requestBody.getServiceName(), requestBody
                    .getServiceMethodName(), requestBody.getArgs())) {
                return invoker;
            }
        }
        throw new InvokerNotFoundRuntimeException(requestBody.getServiceName());
    }
}