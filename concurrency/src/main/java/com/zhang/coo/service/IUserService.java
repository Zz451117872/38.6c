package com.zhang.coo.service;

/**
 * Created by aa on 2018/10/21.
 */
public interface IUserService {
    
    Object addUser(String name, String password);

    Object transfer(Integer fromId, Integer toId, Double amount);


    Object deleteAll();
}
