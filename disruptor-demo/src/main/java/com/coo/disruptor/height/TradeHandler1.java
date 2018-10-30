package com.coo.disruptor.height;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * Created by aa on 2018/10/5.
 */
public class TradeHandler1 implements EventHandler<Trade> , WorkHandler<Trade>{

    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {

        this.onEvent(trade);
    }

    public void onEvent(Trade trade) throws Exception {
        System.out.println("handler 1 set name ");
        trade.setName( "xxx");
        Thread.sleep( 1000 );
    }
}
