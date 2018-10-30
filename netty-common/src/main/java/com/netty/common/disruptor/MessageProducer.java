package com.netty.common.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.netty.common.TranslateData;
import com.netty.common.TranslateDataWarper;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by aa on 2018/10/17.
 */
public class MessageProducer {

    private String producerId;
    private RingBuffer<TranslateDataWarper> ringBuffer;

    public MessageProducer( String producerId, RingBuffer<TranslateDataWarper> ringBuffer ){
        this.ringBuffer = ringBuffer;
        this.producerId = producerId;
    }

    public void sendData(TranslateData data , ChannelHandlerContext chx){

        long sequence = this.ringBuffer.next();
        try{
            TranslateDataWarper warper = ringBuffer.get( sequence );
            warper.setData( data );
            warper.setChannelHandlerContext( chx );
        }finally {
            this.ringBuffer.publish( sequence );
        }
    }
}
