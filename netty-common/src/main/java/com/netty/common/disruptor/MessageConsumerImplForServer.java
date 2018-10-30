package com.netty.common.disruptor;

import com.netty.common.TranslateData;
import com.netty.common.TranslateDataWarper;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by aa on 2018/10/19.
 */
public class MessageConsumerImplForServer extends MessageConsumer {

    public MessageConsumerImplForServer(String customerId) {
        super(customerId);
    }

    public void onEvent(TranslateDataWarper translateDataWarper) throws Exception {

        TranslateData data = translateDataWarper.getData();
        System.out.println( " server: " + data.toString() );

        ChannelHandlerContext chx = translateDataWarper.getChannelHandlerContext();
        TranslateData response = new TranslateData();
        response.setId( "resp " + data.getId() );
        response.setName( "resp " + data.getName() );
        response.setMessage( "resp " + data.getMessage() );

        chx.writeAndFlush( response );
    }
}
