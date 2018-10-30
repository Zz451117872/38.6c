package com.netty.server;

import com.netty.common.TranslateData;
import com.netty.common.disruptor.MessageProducer;
import com.netty.common.disruptor.RingBufferWorkPoolFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by aa on 2018/10/15.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println( "server " + msg);
//        TranslateData data = (TranslateData)msg;
//        System.out.println( "server: " + data.toString() );
//
//
//        TranslateData response = new TranslateData();
//        response.setId( "resp "+data.getId() );
//        response.setName( "resp " + data.getName() );
//        response.setMessage( "resp " + data.getMessage() );

        ctx.writeAndFlush( "resp " + msg );

//        TranslateData data = (TranslateData)msg;
//        String producerId = "001";
//
//        MessageProducer producer = RingBufferWorkPoolFactory.getInstance().getProducer( producerId );
//        producer.sendData( data , ctx );
    }
}
