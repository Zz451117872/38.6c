package com.netty.common.disruptor;

import com.netty.common.TranslateData;
import com.netty.common.TranslateDataWarper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by aa on 2018/10/19.
 */
public class MessageConsumerImplForClient extends MessageConsumer {

    public MessageConsumerImplForClient(String customerId) {
        super(customerId);
    }

    public void onEvent(TranslateDataWarper translateDataWarper) throws Exception {

        TranslateData data = translateDataWarper.getData();
        ChannelHandlerContext chx = translateDataWarper.getChannelHandlerContext();

        try{

            System.out.println( "client : " + data.toString() );
        }finally {
            ReferenceCountUtil.release( data );
        }

    }
}
