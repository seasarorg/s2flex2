package org.seasar.flex2.message.format.amf.io;

import java.io.IOException;

import org.seasar.flex2.message.format.amf.io.Amf3ReaderWriterTest.MyBean;
import org.seasar.flex2.message.io.DataInput;
import org.seasar.flex2.message.io.DataOutput;
import org.seasar.flex2.message.io.Externalizable;

public class TestExternalizeObject implements Externalizable {
    
    private MyBean myBean;
    
    public TestExternalizeObject(){
    }

    public void readExternal(DataInput input) {
        try {
            myBean = (MyBean)input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeExternal(DataOutput output) {
        try {
            output.writeObject(myBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }
}