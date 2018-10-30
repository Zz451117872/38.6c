package com.zhang.coo.dao;

import com.zhang.coo.entry.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by aa on 2018/10/21.
 */
@Mapper
public interface UserDao {

    @Insert(" insert into user(name,password,version) values( #{name},#{password},#{version})")
    int addUser(User user);

    @Delete("delete from user where 1 =1 ")
    int deleteAll();
}
