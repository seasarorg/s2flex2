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
package org.seasar.flex2.util.data.transfer;

import javax.servlet.http.HttpSession;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.flex2.util.data.storage.Storage;
import org.seasar.flex2.util.data.storage.impl.HttpSessionDataStorage;
import org.seasar.framework.container.S2Container;

public class TransferTest extends S2TestCase {

    private final static String PATH = "TransferTest.dicon";

    private Transfer transfer;

    public TransferTest(String name) {
        super(name);
    }

    public void testExportTo() {
        HttpSession session = createMockHttpSession();
        Storage storage = new HttpSessionDataStorage(session);
        TestClass testClass = createTarget();
        testClass.setStrData("moji");
        transfer.exportToStorage(testClass, storage);
        assertEquals("1", session.getAttribute("strData"), testClass
                .getStrData());
    }

    public void testImportTo() {
        HttpSession session = createMockHttpSession();
        session.setAttribute("strData", "moji");
        Storage storage = new HttpSessionDataStorage(session);
        TestClass testClass = createTarget();
        transfer.importToComponent(storage, testClass);
        assertEquals("1", testClass.getStrData(), session
                .getAttribute("strData"));
    }

    private HttpSession createMockHttpSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }

    private TestClass createTarget() {
        TestClass testClass = new TestClass();
        return testClass;
    }

    protected void setUp() throws Exception {
        include(PATH);
        S2Container container = getContainer();
        transfer = (Transfer) container.getComponent(Transfer.class);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}