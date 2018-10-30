package com.coo.disruptor.height;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aa on 2018/10/5.
 */
public class Main {

    public static void main(String[] args) throws Exception{


        ExecutorService executorService = Executors.newFixedThreadPool( 4 );
        ExecutorService executorService1 = Executors.newFixedThreadPool( 8 );

        int ringBufferSize = 1024*1024;

        Disruptor<Trade> disruptor = new Disruptor<Trade>(
                new EventFactory<Trade>() {
                    public Trade newInstance() {
                        return new Trade();
                    }
                },
                ringBufferSize,
                executorService1,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );

        //串形操作
//        disruptor
//                .handleEventsWith( new TradeHandler1() )
//                .handleEventsWith( new TradeHandler2() )
//                .handleEventsWith( new TradeHandler3() );

        //并形操作
//        disruptor.handleEventsWith( new TradeHandler1() );
//        disruptor.handleEventsWith( new TradeHandler2() );
//        disruptor.handleEventsWith( new TradeHandler3() );

        //菱形操作
        disruptor.handleEventsWith( new TradeHandler1()  , new TradeHandler2())
        .handleEventsWith( new TradeHandler3() );


        RingBuffer<Trade> ringBuffer = disruptor.start();

        CountDownLatch latch = new CountDownLatch( 1 );

        executorService.submit( new TradePublisher( latch , disruptor ));

        latch.await();

        disruptor.shutdown();
        executorService.shutdown();
        executorService1.shutdown();

    }
}
