package com.netty.client;

import com.netty.common.TranslateData;
import com.netty.common.disruptor.MessageProducer;
import com.netty.common.disruptor.RingBufferWorkPoolFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by aa on 2018/10/15.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try{
            // response = (TranslateData)msg;
            System.out.println( "client : " + msg );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release( msg );
        }

//        TranslateData response = (TranslateData)msg;
//        String produceId = "client:sss:01";
//        MessageProducer producer = RingBufferWorkPoolFactory.getInstance().getProducer( produceId );
//        producer.sendData( response , ctx);

    }



}
