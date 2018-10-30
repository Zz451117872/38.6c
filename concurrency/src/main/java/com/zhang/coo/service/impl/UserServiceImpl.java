package com.zhang.coo.service.impl;

import com.zhang.coo.dao.AccountDao;
import com.zhang.coo.dao.TransferInfoDao;
import com.zhang.coo.dao.UserDao;
import com.zhang.coo.entry.Account;
import com.zhang.coo.entry.TransferInfo;
import com.zhang.coo.entry.User;
import com.zhang.coo.service.IUserService;
import com.zhang.coo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aa on 2018/10/21.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransferInfoDao transferInfoDao;


    public Object addUser(String name, String password) {
        User user = new User();
        user.setName( name );
        user.setPassword( password );
        user.setVersion(TimeUtils.getVersion() );

        Integer id = userDao.addUser( user );
        if( null == id ){
            System.out.println( " 添加用户失败 1 ： "+ name );
            return null;
        }

        Account account = new Account();
        account.setId( user.getId() );
        account.setAnt( 1000d );
        account.setScore( 0d );
        account.setVersion( TimeUtils.getVersion() );

        int result = accountDao.addAccount( account );
        if( result < 1 ){
            System.out.println( " 添加用户失败 2 ： "+ name );
            return null;
        }
        return true;
    }

    public Object transfer(Integer fromId, Integer toId, Double amount) {

        Account fromAccount = accountDao.selectOne( fromId );
        Account toAccount = accountDao.selectOne( toId );

        fromAccount.setAnt( fromAccount.getAnt() - amount );
        fromAccount.setVersion( TimeUtils.getVersion() );

        toAccount.setAnt( toAccount.getAnt() + amount );
        toAccount.setVersion( TimeUtils.getVersion() );

        int result = accountDao.updateAccount( fromAccount );
        if( result < 1 ){
            System.out.println( "更新用户帐户信息失败 1： " + fromId );
            return null;
        }

        result = accountDao.updateAccount( toAccount );
        if( result < 1 ){
            System.out.println( "更新用户帐户信息失败 2： " + toId );
            return null;
        }

        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setFromId( fromId );
        transferInfo.setToId( toId );
        transferInfo.setAmount( amount );
        transferInfo.setCreateTime( TimeUtils.getCurrentTime() );
        transferInfo.setVersion( TimeUtils.getVersion() );

        Integer id = transferInfoDao.addTransferInfo( transferInfo );
        if( null == id ){
            System.out.println( "保存转账信息失败 1： " + fromId +" to " + toId );
            return null;
        }
        return transferInfo;
    }

    public Object deleteAll() {

        userDao.deleteAll();
        accountDao.deleteAll();
        return null;
    }
}
