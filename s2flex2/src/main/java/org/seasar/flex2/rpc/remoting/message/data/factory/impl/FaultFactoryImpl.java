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
package org.seasar.flex2.rpc.remoting.message.data.factory.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.seasar.flex2.rpc.remoting.message.data.Fault;
import org.seasar.flex2.rpc.remoting.message.data.factory.FaultFactory;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.container.S2Container;

/**
 * {@link Fault}のFactory実装クラスです
 * @author e1.arkw
 * @author nod
 *
 */
public class FaultFactoryImpl implements FaultFactory {

    /**
     * StackTraceを文字列にして返します
     * @param t StackTraceを出力する対象
     * @return　StackTraceの内容を文字列化したもの
     */
    private static final String getStackTraceString(final Throwable t) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        return sw.toString();
//        final StackTraceElement[] elements = t.getStackTrace();
//        final StringBuffer buf = new StringBuffer(t.toString());
//        buf.append('\n');
//        for (int i = 0; i < elements.length; ++i) {
//            buf.append(elements[i].toString());
//            buf.append('\n');
//        }
//       return t.toString();
    }

    /**
     * @exclude
     */
    private S2Container container;

    /**
     * 　{@link Fault}を生成します
     * @param type タイプ
     * @param details　詳細メッセージ
     * @param description　エラー情報
     * @param rootCause　発生元の例外
     * @return　Faultのインスタンス
     */
    public Fault createFault(final String type, final String details,
            final String description,final Throwable throwable) {
        final Fault fault = (Fault) container.getComponent(Fault.class);
        fault.setType(type);
        fault.setFaultDetail(details);
        fault.setFaultString(description);
//        Throwable rootCause = throwable.getCause();
        fault.setRootCause(createMap(throwable,true));
//        
//        if(rootCause!= null && !throwable.equals(rootCause)){
//           fault.setRootCause(createMap(rootCause));
//        }else{
//            fault.setRootCause(createMap(throwable));
//        }
        return fault;
    }
    /**
     * 例外クラスからMapを作成して返します
     * @param t 例外クラス
     * @param hierarchicalAcquisition rootCauseを取得するときにはtrue,それ以外はfalse
     * @return　例外クラスのプロパティをセットしたMapクラス
     */
    
    private Map createMap(final Throwable t, final boolean hierarchicalAcquisition) {
        Map m = new HashMap();
        final BeanDesc beanDesc = BeanDescFactory.getBeanDesc(t.getClass());
        PropertyDesc propertyDesc = null;
        for (int i = 0; i < beanDesc.getPropertyDescSize(); ++i) {
            propertyDesc = beanDesc.getPropertyDesc(i);
            if ("stackTrace".equals(propertyDesc.getPropertyName())) {
                continue;
            }
            if("cause".equals(propertyDesc.getPropertyName())  ){
                Throwable cause = (Throwable)propertyDesc.getValue(t);
                if(cause!=null){
                    m.put(propertyDesc.getPropertyName(),createMap(cause,false));
                }
                continue;
            }
            if(propertyDesc.getValue(t) instanceof Throwable){
                continue;
            }
            
            if (propertyDesc.isReadable() && propertyDesc.isWritable()) {
                m.put(propertyDesc.getPropertyName(), propertyDesc.getValue(t));
            } else if (propertyDesc.hasReadMethod()
                    && propertyDesc.hasWriteMethod()) {
                m.put(propertyDesc.getPropertyName(), propertyDesc.getValue(t));
            }
        }
        return m;
    }
    
    /**
     * [{@link Fault}を生成して返します
     * @param throwable 発生した例外
     * @return 例外をもとに生成したFault クラス
     */
    public Fault createFault(final Throwable throwable) {
        return createFault(throwable.getClass().getName(),
                getStackTraceString(throwable), throwable.getMessage(),throwable);
    }

    /**
     * @exclude
     */
    public void setContainer(final S2Container container) {
        this.container = container;
    }
}