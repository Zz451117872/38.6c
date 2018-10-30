package com.netty.common;

import com.lmax.disruptor.ExceptionHandler;

/**
 * Created by aa on 2018/10/18.
 */
public class DisruptorExceptorHandler implements ExceptionHandler<TranslateDataWarper> {


    public void handleEventException(Throwable throwable, long l, TranslateDataWarper translateDataWarper) {

    }

    public void handleOnStartException(Throwable throwable) {

    }

    public void handleOnShutdownException(Throwable throwable) {

    }
}
