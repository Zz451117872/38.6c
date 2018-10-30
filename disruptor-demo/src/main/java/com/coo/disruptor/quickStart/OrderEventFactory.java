package com.coo.disruptor.quickStart;

import com.lmax.disruptor.EventFactory;

/**
 * Created by aa on 2018/10/1.
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
