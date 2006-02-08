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
package flashgateway.io;

import java.util.HashMap;

public class ASObject extends HashMap {

	private String type_;

	public ASObject() {
	}

	public ASObject(String type) {
		type_ = type;
	}

	public String getType() {
		return type_;
	}

	public void setType(String type) {
		type_ = type;
	}

	public boolean containsKey(Object key) {
		return super.containsKey(toLowerCase(key));
	}

	public Object get(Object key) {
		return super.get(toLowerCase(key));
	}

	public Object put(Object key, Object value) {
		return super.put(toLowerCase(key), value);
	}

	public Object remove(Object key) {
		return super.remove(toLowerCase(key));
	}

	private Object toLowerCase(Object key) {
		if (key != null && key instanceof String) {
			key = ((String) key).toLowerCase();
		}
		return key;
	}

	public String toString() {
		return "ASObject[type=" + getType() + "," + super.toString() + "]";
	}

}