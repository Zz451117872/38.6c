package com.coo.disruptor.multi;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by aa on 2018/10/6.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                new EventFactory<Order>() {
                    public Order newInstance() {
                        return new Order();
                    }
                },
                1024* 1024 ,
                new YieldingWaitStrategy()
        );

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();


        OrderConsumer[] consumers = new OrderConsumer[ 10 ];
        for( int i=0; i<consumers.length ; i++ ){
            consumers[i] = new OrderConsumer( "consumer"+ i);
        }

        WorkerPool<Order> workerPool = new WorkerPool<Order>(
                ringBuffer,
                sequenceBarrier,
                new OrderExceptionHandler(),
                consumers
        );

        ringBuffer.addGatingSequences( workerPool.getWorkerSequences() );

        workerPool.start(Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() ));


        final CountDownLatch latch = new CountDownLatch( 1 );

        for( int i=0; i< 20 ; i++ ){

            final OrderProducer producer = new OrderProducer( ringBuffer );

            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for( int j=0; j<100; j++){
                        producer.sendData( UUID.randomUUID().toString() );
                    }
                }
            }).start();
        }


        Thread.sleep( 2000 );
        latch.countDown();
        Thread.sleep(8000 );

        System.out.println( "消费者处理的消息 ："+ consumers[2].getCount() );
    }



}
