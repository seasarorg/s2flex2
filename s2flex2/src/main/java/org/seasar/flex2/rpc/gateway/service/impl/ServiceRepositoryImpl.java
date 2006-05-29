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
package org.seasar.flex2.rpc.gateway.service.impl;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.seasar.flex2.rpc.gateway.service.ServiceRepository;

public abstract class ServiceRepositoryImpl implements ServiceRepository {

    protected final Map serviceCache = Collections
            .synchronizedMap(new WeakHashMap(64));

    public void addService(String serviceName, Object service) {
        serviceCache.put(serviceName, service);
    }

    public void clearService() {
        serviceCache.clear();
    }

    public Object getService(final String serviceName) {
        Object service = serviceCache.get(serviceName);
        return service;
    }

    public boolean hasService(String serviceName) {
        return serviceCache.containsKey(serviceName);
    }

    public void removeService(String serviceName) {
        serviceCache.remove(serviceName);
    }
}