package com.coo.disruptor.quickStart;

import java.io.Serializable;

/**
 * Created by aa on 2018/10/1.
 */
public class OrderEvent implements Serializable {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
