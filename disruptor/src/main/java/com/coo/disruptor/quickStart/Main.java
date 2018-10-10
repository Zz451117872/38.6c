package com.coo.disruptor.quickStart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aa on 2018/10/1.
 */
public class Main {

    public static void main(String[] args){

        OrderEventFactory OrderEvent = new OrderEventFactory();
        int ringBufferSize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() );

        Disruptor<OrderEvent> disruptor= new Disruptor<OrderEvent>(
                OrderEvent,
                ringBufferSize,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );


        //添加消费者监听
        disruptor.handleEventsWith( new OrderEventHandler() );

        disruptor.start();

        //实际存储数据的容器
        RingBuffer<OrderEvent> buffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer( buffer );

        ByteBuffer bb = ByteBuffer.allocate( 8 );

        for( long i=0; i<20 ; i++){

            bb.putLong( 0 , i);
            producer.sendData( bb );
        }


        disruptor.shutdown();
        executorService.shutdown();
    }
}
