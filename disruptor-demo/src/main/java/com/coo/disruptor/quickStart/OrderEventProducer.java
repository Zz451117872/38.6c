package com.coo.disruptor.quickStart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by aa on 2018/10/2.
 */
public class OrderEventProducer {


    private RingBuffer<OrderEvent> buffer;

    public OrderEventProducer( RingBuffer<OrderEvent> buffer){
        this.buffer = buffer;
    }


    public void sendData(ByteBuffer byteBuffer){

        //获取一个可用的序号
        long sequence = sequence = buffer.next();;
        try{

            //根据这个序号找到具体的元素
            OrderEvent orderEvent = buffer.get( sequence );
            //赋值
            orderEvent.setValue( byteBuffer.getLong(0) );
        }finally {
            //发布
            buffer.publish( sequence );
        }
    }
}
