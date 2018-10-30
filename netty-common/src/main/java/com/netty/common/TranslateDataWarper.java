package com.netty.common;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

/**
 * Created by aa on 2018/10/17.
 */
public class TranslateDataWarper implements Serializable{

    TranslateData data;

    ChannelHandlerContext channelHandlerContext;

    public TranslateData getData() {
        return data;
    }

    public void setData(TranslateData data) {
        this.data = data;
    }

    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }

    public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.channelHandlerContext = channelHandlerContext;
    }
}
