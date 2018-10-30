package com.netty.client;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.netty.common.MarshallerCodeFactory;
import com.netty.common.TranslateData;
import com.netty.common.disruptor.MessageConsumer;
import com.netty.common.disruptor.MessageConsumerImplForClient;
import com.netty.common.disruptor.MessageConsumerImplForServer;
import com.netty.common.disruptor.RingBufferWorkPoolFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by aa on 2018/10/15.
 */
public class NettyClient {

    public static final String host = "127.0.0.1";
    public static final int port = 8881;
    private  Channel channel;
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    ChannelFuture future ;

    public NettyClient(){

        this.connertion( host , port);
    }

    private void connertion(String host, int port) {



        Bootstrap serverBootstrap = new Bootstrap();
        try{

            serverBootstrap.group( workGroup )
                    .channel(NioSocketChannel.class )
                    .option( ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT  )
                    .option( ChannelOption.ALLOCATOR , PooledByteBufAllocator.DEFAULT)
                    .handler( new LoggingHandler(LogLevel.INFO ))
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            socketChannel.pipeline().addLast( new StringDecoder());
                            socketChannel.pipeline().addLast( new StringEncoder());
                            //socketChannel.pipeline().addLast( MarshallerCodeFactory.buildMarshallingDecoder() );
                            //socketChannel.pipeline().addLast( MarshallerCodeFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast( new ClientHandler() );
                        }
                    });



            future = serverBootstrap.connect( host ,port).sync();
            System.out.println( "client start.....");

            this.channel = future.channel();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sendData(){
        for( int i=0; i<10; i++){
            TranslateData data = new TranslateData();
            data.setId( i + " id");
            data.setName( i + " name");
            data.setMessage( i + " message");
            this.channel.writeAndFlush( "i" + i );

            //System.out.println( "send:" + data.toString() );
        }
    }


    public void close()throws Exception{
        future.channel().closeFuture().sync();
        workGroup.shutdownGracefully();
        System.out.println( "client stop.....");
    }


    public static void main(String[] args){

//
//        MessageConsumer[] consumers = new MessageConsumer[ 4];
//        for( int i=0; i<consumers.length; i++){
//            MessageConsumer consumer = new MessageConsumerImplForClient("client:id:"+i);
//
//            consumers[i] = consumer;
//        }
//
//        RingBufferWorkPoolFactory.getInstance().initAntStart(
//                ProducerType.MULTI,
//                1024*1024,
//                new BlockingWaitStrategy(),
//                consumers
//        );

        new NettyClient().sendData();

    }
}
