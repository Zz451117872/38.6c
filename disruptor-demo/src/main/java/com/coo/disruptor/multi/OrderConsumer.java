package com.coo.disruptor.multi;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aa on 2018/10/6.
 */
public class OrderConsumer implements WorkHandler<Order> {

    private String customerId ;
    private static AtomicInteger count = new AtomicInteger( 0 );

    public OrderConsumer(String customerId){
        this.customerId = customerId;
    }

    private Random random = new Random();

    public void onEvent(Order order) throws Exception {
        Thread.sleep( 1 * random.nextInt(10) );
        System.out.println( "消费者 ： "+ this.customerId +" ; 消息信息："+ order.getId() );
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }
}
