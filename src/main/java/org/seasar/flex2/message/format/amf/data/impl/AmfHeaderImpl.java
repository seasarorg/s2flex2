package org.seasar.flex2.message.format.amf.data.impl;

import org.seasar.flex2.message.format.amf.data.AmfHeader;

public class AmfHeaderImpl implements AmfHeader {

    private final Object value;

    private int length = -1;

    private final String name;

    private boolean required = false;

    public AmfHeaderImpl(String name, Object data) {
        this.name = name;
        this.value = data;
    }

    public Object getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}