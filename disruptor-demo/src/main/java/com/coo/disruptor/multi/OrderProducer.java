package com.coo.disruptor.multi;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by aa on 2018/10/6.
 */
public class OrderProducer {

    private RingBuffer<Order> ringBuffer;

    public OrderProducer( RingBuffer<Order> ringBuffer ){
        this.ringBuffer = ringBuffer;
    }

    public void sendData( String data ){
        long sequence = ringBuffer.next();
        try{
            Order order = ringBuffer.get( sequence );
            order.setId( data );
        }finally {
            ringBuffer.publish( sequence );
        }
    }
}
