package com.zhang.coo.service.impl;

import com.zhang.coo.dao.AccountDao;
import com.zhang.coo.dao.UserDao;
import com.zhang.coo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aa on 2018/10/21.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;



}
