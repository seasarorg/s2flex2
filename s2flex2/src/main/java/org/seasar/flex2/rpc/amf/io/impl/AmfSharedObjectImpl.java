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
package org.seasar.flex2.rpc.amf.io.impl;

import java.util.ArrayList;

import org.seasar.flex2.rpc.amf.io.AmfSharedObject;

public class AmfSharedObjectImpl implements AmfSharedObject {

    protected ArrayList sharedObjects;

    public AmfSharedObjectImpl() {
        sharedObjects = new ArrayList(256);
    }

    public void addSharedObject(Object o) {
        sharedObjects.add(o);
    }

    public int getSharedIndex(Object o) {
        for (int i = 0; i < sharedObjects.size(); ++i) {
            if (o == sharedObjects.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public int getSize() {
        return sharedObjects.size();
    }

    public void initialize() {
        sharedObjects.clear();
    }

    public Object getSharedObject(int index) {
        return sharedObjects.get(index);
    }
}