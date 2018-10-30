package com.netty.common.disruptor;

import com.lmax.disruptor.WorkHandler;
import com.netty.common.TranslateDataWarper;

/**
 * Created by aa on 2018/10/17.
 */
public abstract class MessageConsumer implements WorkHandler<TranslateDataWarper> {

    private String customerId;

    public MessageConsumer(String customerId){
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
