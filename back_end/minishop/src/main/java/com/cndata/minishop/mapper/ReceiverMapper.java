package com.cndata.minishop.mapper;

import com.cndata.minishop.domain.Receiver;
import com.cndata.minishop.domain.ReceiverExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiverMapper {
    int countByExample(ReceiverExample example);

    int deleteByExample(ReceiverExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Receiver record);

    int insertSelective(Receiver record);

    List<Receiver> selectByExample(ReceiverExample example);

    Receiver selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Receiver record, @Param("example") ReceiverExample example);

    int updateByExample(@Param("record") Receiver record, @Param("example") ReceiverExample example);

    int updateByPrimaryKeySelective(Receiver record);

    int updateByPrimaryKey(Receiver record);
}