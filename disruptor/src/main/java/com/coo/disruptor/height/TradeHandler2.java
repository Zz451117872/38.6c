package com.coo.disruptor.height;


import com.lmax.disruptor.EventHandler;

import java.util.UUID;

/**
 * Created by aa on 2018/10/5.
 */
public class TradeHandler2 implements EventHandler<Trade> {

    public void onEvent(Trade trade, long sequence, boolean b) throws Exception {

        System.out.println("handler 2 set id ");
        trade.setId(UUID.randomUUID().toString() );
        Thread.sleep( 2000 );
    }
}
