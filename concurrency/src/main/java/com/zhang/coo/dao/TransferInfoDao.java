package com.zhang.coo.dao;

import com.zhang.coo.entry.TransferInfo;
import org.apache.ibatis.annotations.Insert;

/**
 * Created by aa on 2018/10/21.
 */
public interface TransferInfoDao {

    @Insert("insert into transferInfo(fromId,toId,amount,createTime,version) values( #{fromId},#{toId},#{amount},#{createTime},#{version})")
    int addTransferInfo(TransferInfo transferInfo);
}
