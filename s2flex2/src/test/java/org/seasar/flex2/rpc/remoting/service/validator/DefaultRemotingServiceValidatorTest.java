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
package org.seasar.flex2.rpc.remoting.service.validator;

import org.seasar.extension.unit.S2TestCase;

public class DefaultRemotingServiceValidatorTest extends S2TestCase {

    private static String PATH = "RemotingServiceMethodNameValidatorTest.dicon";

    private RemotingServiceValidator remotingServiceValidator;

    public void testIsValidator() throws Exception {
        assertNotNull("1", remotingServiceValidator);
        assertFalse("2", remotingServiceValidator.isValidate(
                "java.lang.Object", "getClass", null));
        assertTrue("3", remotingServiceValidator.isValidate(
                "test.Service", "getData", null));
    }

    protected void setUp() throws Exception {
        include(PATH);
    }

    protected void tearDown() throws Exception {
    }
}
