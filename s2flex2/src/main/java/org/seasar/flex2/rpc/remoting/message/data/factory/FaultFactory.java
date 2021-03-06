/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.flex2.rpc.remoting.message.data.factory;

import org.seasar.flex2.rpc.remoting.message.data.Fault;

/**
 *  FaultのFactoryインターフェースです
 * @author e1.arkw
 * @author nod
 *　例外を元に{@link Fault}を生成します
 */
public interface FaultFactory {
    /**
     * AMFMessageを処理中に発生した例外からFaultを生成します
     * @param throwable 例外
     * @return Faultのインスタンス
     */
    Fault createFault(Throwable throwable);
}
