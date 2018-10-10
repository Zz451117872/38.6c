package com.coo.disruptor.height;

import com.lmax.disruptor.EventHandler;

/**
 * Created by aa on 2018/10/5.
 */
public class TradeHandler3 implements EventHandler<Trade> {

    public void onEvent(Trade trade, long sequence, boolean endOfBatch) throws Exception {

        System.out.println( "handler 3  name :" + trade.getName() +" id:"+ trade.getId() );
    }
}
