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
package org.seasar2.flex2.rpc.remoting.service.browser {
    
    [RemoteClass(alias="org.seasar2.flex2.rpc.remoting.service.browser.ServiceDetail")]
    public class ServiceDetail
    {
        public var name:String;
    
        public var className:String;
    
        public var interfaces:Array;
    
        public var methodDetails:Array;
    }
}