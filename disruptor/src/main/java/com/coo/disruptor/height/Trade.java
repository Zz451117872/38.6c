package com.coo.disruptor.height;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aa on 2018/10/5.
 */
public class Trade implements Serializable {

    private String id;
    private String name;
    private Double price;
    private AtomicInteger count = new AtomicInteger( 0 );

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
