package com.coo.disruptor.height;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by aa on 2018/10/5.
 */
public class TradePublisher implements  Runnable {

    CountDownLatch latch;
    Disruptor<Trade> disruptor;



    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {

        this.latch = latch;
        this.disruptor = disruptor;
    }


    public void run() {

        TradeEventTranslator translator = new TradeEventTranslator();
        for( int i=0; i<10 ; i++){
            disruptor.publishEvent( translator );

        }
        latch.countDown();
    }
}

class TradeEventTranslator implements EventTranslator<Trade>{

    private Random r = new Random();

    public void translateTo(Trade trade, long sequence) {
        this.generateTrade( trade );
    }

    private void generateTrade(Trade trade) {
        trade.setPrice( r.nextDouble() * 1000 );
    }
}
