package com.netty.common.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import com.netty.common.DisruptorExceptorHandler;
import com.netty.common.TranslateData;
import com.netty.common.TranslateDataWarper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Created by aa on 2018/10/17.
 */
public class RingBufferWorkPoolFactory {

    private static class SingletonHolder{

        static final RingBufferWorkPoolFactory factory= new RingBufferWorkPoolFactory();
    }

    private RingBufferWorkPoolFactory(){

    }

    public static RingBufferWorkPoolFactory getInstance( ){
        return SingletonHolder.factory;
    }

    private static Map<String,MessageProducer> producers = new ConcurrentHashMap<String, MessageProducer>();
    private static Map<String,MessageConsumer> consumers = new ConcurrentHashMap<String, MessageConsumer>();

    private RingBuffer<TranslateDataWarper> ringBuffer;

    private WorkerPool<TranslateDataWarper> workerPool;

    private SequenceBarrier sequenceBarrier;

    public void initAntStart(ProducerType type , int ringBufferSize , WaitStrategy waitStrategy , MessageConsumer[] messageConsumers){

        this.ringBuffer = RingBuffer.create(type,
                new EventFactory<TranslateDataWarper>() {
                    public TranslateDataWarper newInstance() {
                        return new TranslateDataWarper();
                    }

                    } , ringBufferSize , waitStrategy );

        this.sequenceBarrier = this.ringBuffer.newBarrier();

        this.workerPool = new WorkerPool<TranslateDataWarper>(
                this.ringBuffer , this.sequenceBarrier,
                new DisruptorExceptorHandler(),
                messageConsumers
        );

        for ( MessageConsumer mc : messageConsumers){
            this.consumers.put( mc.getCustomerId() , mc );
        }

        this.ringBuffer.addGatingSequences( this.workerPool.getWorkerSequences() );

        this.workerPool.start(Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors() ));


    }


    public MessageProducer getProducer( String producerId){

        MessageProducer producer = this.producers.get( producerId );
        if( null == producer ){
            producer = new MessageProducer( producerId ,this.ringBuffer ) ;
            this.producers.put( producerId , producer );
        }
        return producer;
    }

}
