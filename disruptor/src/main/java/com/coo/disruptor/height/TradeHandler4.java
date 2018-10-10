package com.coo.disruptor.height;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by aa on 2018/10/5.
 */
public class TradeHandler4 implements EventHandler<Trade> {

    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.out.println("handler 4 add price ");
        trade.setPrice( trade.getPrice() + 10000 );
        Thread.sleep( 1000 );
    }
}
