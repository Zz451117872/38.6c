package com.zhang.coo.dao;

import com.zhang.coo.entry.Account;
import org.apache.ibatis.annotations.*;

/**
 * Created by aa on 2018/10/21.
 */

@Mapper
public interface AccountDao {

    @Insert("insert into account(ant,score,version) values(#{ant},#{score},#{version})")
    int addAccount(Account account);

    @Delete("delete from account where 1=1")
    int deleteAll();


    @Results({
            @Result( property = "id" , column = "id" , id = true),
            @Result( property = "ant" , column = "ant" ),
            @Result( property = "score" , column = "score" ),
            @Result( property = "version" , column = "id" )
        }
    )
    @Select("select * from account where id = #{fromId}")
    Account selectOne(Integer fromId);

    @Update( "update account  set ant = #{ant} ,et score = #{score} , set version= #{version} where id = #{id}")
    int updateAccount(Account fromAccount);
}
