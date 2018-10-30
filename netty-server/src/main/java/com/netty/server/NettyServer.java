package com.netty.server;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.netty.common.MarshallerCodeFactory;
import com.netty.common.disruptor.MessageConsumer;
import com.netty.common.disruptor.MessageConsumerImplForServer;
import com.netty.common.disruptor.RingBufferWorkPoolFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PoolArenaMetric;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by aa on 2018/10/15.
 */
public class NettyServer {

    public NettyServer(){

        EventLoopGroup bossGroup = new NioEventLoopGroup( 4);
        EventLoopGroup workGroup = new NioEventLoopGroup( 10);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try{

            serverBootstrap.group( bossGroup , workGroup )
                .channel(NioServerSocketChannel.class )
                //.option(ChannelOption.SO_BACKLOG , 1024)
                //.option( ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT  )
                //.option( ChannelOption.ALLOCATOR , PooledByteBufAllocator.DEFAULT)
                //.handler( new LoggingHandler(LogLevel.INFO ))
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        socketChannel.pipeline().addLast( new StringDecoder());
                        socketChannel.pipeline().addLast( new StringEncoder());
                        //socketChannel.pipeline().addLast(MarshallerCodeFactory.buildMarshallingDecoder() );
                        //socketChannel.pipeline().addLast(MarshallerCodeFactory.buildMarshallingEncoder() );
                        socketChannel.pipeline().addLast( new ServerHandler() );
                    }
                });



            ChannelFuture future = serverBootstrap.bind( "127.0.0.1" ,8881).sync();
            System.out.println( "server start.....");
            future.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
            System.out.println( "server stop.....");
        }

    }


    public static void main(String[] args){

//
//        MessageConsumer[] consumers = new MessageConsumer[ 4];
//        for( int i=0; i<consumers.length; i++){
//            MessageConsumer consumer = new MessageConsumerImplForServer("server:id:"+i);
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

        new NettyServer();
    }
}
