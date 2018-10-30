package com.coo.disruptor.quickStart;


import com.lmax.disruptor.EventHandler;

/**
 * Created by aa on 2018/10/1.
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    public void onEvent(OrderEvent orderEvent, long sequence, boolean endofBatch) throws Exception {
        System.out.println(" +++++++++++++++:" + orderEvent.getValue() );
    }
}
